package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Event;
import edu.kpi.iasa.mmsa.club.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(value = "/events")
    public ResponseEntity<String> createEvent(@Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping(value = "/events")
    public ResponseEntity<List<Event>> readAll() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping(value = "/events/rank/{rankId}")
        public ResponseEntity<List<Event>> readAllByRank(@PathVariable long rankId) {
        return ResponseEntity.ok(eventService.getAllEventsByRank(rankId));
    }

    @GetMapping(value = "/events/organizer/{login}")
    public ResponseEntity<List<Event>> readAllByOrganizer(@PathVariable("login") String login) {
        return ResponseEntity.ok(eventService.getAllEventsByOrganizer(login));
    }

    @GetMapping(value = "/events/date/{date}")
    public ResponseEntity<List<Event>> readAllByDate(@PathVariable String date) {
        return ResponseEntity.ok(eventService.getAllEventsByDate(date));
    }

    @GetMapping(value = "/events/cost/{cost}")
    public ResponseEntity<List<Event>> readAllByCost(@PathVariable Double cost) {
        return ResponseEntity.ok(eventService.getAllEventsByCost(cost));
    }

    @PutMapping(value = "/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping(value = "/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }
}
