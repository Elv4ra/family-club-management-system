package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.EventAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.EventNotFoundException;
import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
import edu.kpi.iasa.mmsa.club.exception.TimeParseException;
import edu.kpi.iasa.mmsa.club.repository.EventRepository;
import edu.kpi.iasa.mmsa.club.repository.FinancesRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Event;
import edu.kpi.iasa.mmsa.club.repository.model.Finances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final FinancesRepository financesRepository;

    @Autowired
    public EventService(EventRepository eventRepository, FinancesRepository financesRepository) {
        this.eventRepository = eventRepository;
        this.financesRepository = financesRepository;
    }

    public String createEvent(Event event) {
        if (!((eventRepository.findByDate(event.getDate()).isPresent() && eventRepository.findByEventName(event.getEventName()).isPresent()) || (event.getDate().before(new Timestamp(System.currentTimeMillis()))))) {
            try {
                eventRepository.save(event);
                Optional<Event> newEvent = eventRepository.findByEventName(event.getEventName());
                financesRepository.save(new Finances(newEvent.get().getOrganizer(), newEvent.get()));
                return "New Event was successfully created";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new EventAlreadyExistsException();
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new EventNotFoundException();
        }
        return  events;
    }

    public List<Event> getAllEventsByRank(long rankId) {
        List<Event> events = eventRepository.findAll(), result = new ArrayList<>();
        for (Event event : events) {
            if (event.getEventRank().getId() == rankId) {
                result.add(event);
            }
        }
        if (result.isEmpty()) {
            throw new EventNotFoundException();
        }
        return result;
    }

    public List<Event> getAllEventsByOrganizer(String name) {
        List<Event> events = eventRepository.findAll(), result = new ArrayList<>();
        for (Event event: events) {
            if ((event.getOrganizer().getLogin().equals(name))
            || (event.getOrganizer().getName().equals(name))
            || (event.getOrganizer().getAlias().equals(name))){
                result.add(event);
            }
        }
        if (result.isEmpty()) {
            throw new EventNotFoundException();
        }
        return result;
    }

    public List<Event> getAllEventsByDate(String input) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date temp = formatter.parse(input);
            Timestamp date = new Timestamp(temp.getTime());
            List<Event> events = eventRepository.findAll(), result = new ArrayList<>();
            for (Event event : events) {
                if (event.getDate().before(new Timestamp(date.getTime()+Long.parseLong("93600000")))
                        && event.getDate().after(new Timestamp(date.getTime()-Long.parseLong("79200000"))))
                {
                    result.add(event);
                }
            }
            if (result.isEmpty()) {
                throw new EventNotFoundException();
            }
            return result;
        } catch (ParseException e) {
            throw new TimeParseException();
        }
    }

    public List<Event> getAllEventsByCost(Double cost) {
        List<Event> events = eventRepository.findAll(), result = new ArrayList<>();
        for (Event event: events) {
            if (event.getCost() <= cost) {
                result.add(event);
            }
        }
        if (result.isEmpty()) {
            throw new EventNotFoundException();
        }
        return result;
    }

    public String updateEvent(long id, Event event) {
        Optional<Event> maybeOldEvent = eventRepository.findById(id);
        if (maybeOldEvent.isPresent()) {
            try {
                updating(maybeOldEvent.get(), event);
                eventRepository.save(maybeOldEvent.get());
                return "Event with id="+String.valueOf(id)+" was successfully updated";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new EventNotFoundException();
    }

    private void updating(Event oldEvent, Event newEvent) {
        if (newEvent.getEventName() != null && !newEvent.getEventName().isBlank()) oldEvent.setEventName(newEvent.getEventName());
        if (newEvent.getEventRank() != null) oldEvent.setEventRank(newEvent.getEventRank());
        if (newEvent.getDate() != null && newEvent.getDate().after(new Timestamp(System.currentTimeMillis()))) oldEvent.setDate(newEvent.getDate());
        if (newEvent.getCost() != null && newEvent.getCost() >= 0) oldEvent.setCost(newEvent.getCost());
        if (newEvent.getDuration() != null && !newEvent.getDuration().isBlank()) oldEvent.setDuration(newEvent.getDuration());
        if (newEvent.getPlace() != null && !newEvent.getPlace().isBlank()) oldEvent.setPlace(newEvent.getPlace());
        if (newEvent.getFreeSpots() != null && newEvent.getFreeSpots() > 1) oldEvent.setFreeSpots(newEvent.getFreeSpots());
    }

    public String deleteEvent(long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
            return "Event \""+event.get().getEventName()+"\" was successfully deleted";
        }
        throw new EventNotFoundException();
    }
}
