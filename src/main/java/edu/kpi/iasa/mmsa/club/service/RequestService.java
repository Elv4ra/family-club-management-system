package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.RequestRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Request;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
