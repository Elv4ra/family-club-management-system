package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Request;
import edu.kpi.iasa.mmsa.club.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<String> createRequest(@Valid @RequestBody Request request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<Request>> readAll() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Request> readById(@PathVariable long id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @GetMapping(value = "/{rankOrRole}")
    public ResponseEntity<List<Request>> readAllByAim(@PathVariable String rankOrRole) {
        return ResponseEntity.ok(requestService.getAllRequestsByRankOrRole(rankOrRole));
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<Request>> readAllByStatus(@PathVariable String status) {
        return ResponseEntity.ok(requestService.getAllRequestsByStatus(status));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRequest(@PathVariable long id, @Valid @RequestBody Request request) {
        return ResponseEntity.ok(requestService.updateRequest(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable long id) {
        return ResponseEntity.ok(requestService.deleteRequest(id));
    }
}

