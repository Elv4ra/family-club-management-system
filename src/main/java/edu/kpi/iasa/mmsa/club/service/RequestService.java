package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.InvalidEnumDataException;
import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
import edu.kpi.iasa.mmsa.club.exception.RequestNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.RequestRepository;
import edu.kpi.iasa.mmsa.club.repository.model.RankOrRole;
import edu.kpi.iasa.mmsa.club.repository.model.Request;
import edu.kpi.iasa.mmsa.club.repository.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) {
            throw new RequestNotFoundException();
        }
        return requests;
    }

    public String createRequest(Request request) {
        try{
            requestRepository.save(request);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputDataException();
        }
        return "New Request was successfully created";
    }

    public Request getRequestById(long id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            return  request.get();
        }
        throw new RequestNotFoundException();
    }

    public List<Request> getAllRequestsByRankOrRole(String rankOrRole) {
        try {
            RankOrRole.valueOf(rankOrRole);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumDataException();
        }
        List<Request> requests = requestRepository.findAll(), result = new ArrayList<>();
        for (Request request : requests) {
            if (request.getObjectOfChanging().equals(RankOrRole.valueOf(rankOrRole))) {
                result.add(request);
            }
        }
        if (result.isEmpty()) {
            throw new RequestNotFoundException();
        }
        return result;
    }

    public List<Request> getAllRequestsByStatus(String status) {
        try {
            Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumDataException();
        }
        List<Request> requests = requestRepository.findAll(), result = new ArrayList<>();
        for (Request request : requests) {
            if (request.getStatus().equals(Status.valueOf(status))) {
                result.add(request);
            }
        }
        if (result.isEmpty()) {
            throw new RequestNotFoundException();
        }
        return result;
    }

    public String updateRequest(long id, Request request) {
        Optional<Request> maybeRequest = requestRepository.findById(id);
        if (maybeRequest.isPresent()) {
            updating(maybeRequest.get(), request);
            try {
                requestRepository.save(maybeRequest.get());
                return "Request with id="+String.valueOf(id)+" was successfully updated";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new RequestNotFoundException();
    }

    private void updating(Request oldRequest, Request request) {
        if (request.getStatus() != null) oldRequest.setStatus(request.getStatus());
        if (request.getObjectOfChanging() != null) oldRequest.setObjectOfChanging(request.getObjectOfChanging());
        if (request.getDescription() != null && !request.getDescription().isBlank()) oldRequest.setDescription(request.getDescription());
        if (request.getModifier() != null) oldRequest.setModifier(request.getModifier());
    }

    public String deleteRequest(long id) {
        if (requestRepository.findById(id).isPresent()) {
            requestRepository.deleteById(id);
            return "Request with id="+String.valueOf(id)+" was successfully deleted";
        }
        throw new RequestNotFoundException();
    }
}
