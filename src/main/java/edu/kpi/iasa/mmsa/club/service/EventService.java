package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.EventRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
