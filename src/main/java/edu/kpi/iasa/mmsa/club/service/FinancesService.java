package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.FinancesAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.FinancesNotFoundException;
import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
import edu.kpi.iasa.mmsa.club.repository.EventRepository;
import edu.kpi.iasa.mmsa.club.repository.FinancesRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Event;
import edu.kpi.iasa.mmsa.club.repository.model.Finances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class FinancesService {

    private final FinancesRepository financesRepository;
    private final EventRepository eventRepository;

    @Autowired
    public FinancesService(FinancesRepository financesRepository, EventRepository eventRepository) {
        this.financesRepository = financesRepository;
        this.eventRepository = eventRepository;
    }

    public List<Finances> getAllFinances() {
        List<Finances> finances = financesRepository.findAll();
        if (!finances.isEmpty()) {
            return finances;
        }
        throw new FinancesNotFoundException();
    }

    public List<Finances> getAllFinancesByEvent(long id) {
        List<Finances> finances = financesRepository.findAll(), result = new ArrayList<>();
        for (Finances finance : finances) {
            if (finance.getEvent().getId() == id) {
                result.add(finance);
            }
        }
        if (!result.isEmpty()) {
            return result;
        }
        throw new FinancesNotFoundException();
    }

    public List<Finances> getAllFinancesByPaid(Boolean bool) {
        List<Finances> finances = financesRepository.findAll(), result = new ArrayList<>();
        for (Finances finance : finances) {
            if (finance.getAlreadyPaid() == bool) {
                result.add(finance);
            }
        }
        if (!result.isEmpty()) {
            return result;
        }
        throw new FinancesNotFoundException();
    }

    public String createFinance(Finances finance) {
        List<Finances> finances = financesRepository.findAll();
        for (Finances record : finances) {
            if (finance.getMember().getId() == record.getMember().getId()
                && finance.getEvent().getId() == record.getEvent().getId()) {
                throw new FinancesAlreadyExistsException();
            }
        }
        try {
            financesRepository.save(finance);
            Optional<Event> event = eventRepository.findById(finance.getEvent().getId());
            event.get().setOccupiedSpots(event.get().getOccupiedSpots()+1);
            eventRepository.save(event.get());
            return "New record for "+finance.getEvent().getEventName()+" was successfully created by "
                    +finance.getMember().getLogin()+". "
                    +finance.getEvent().getOccupiedSpots().toString()+" free spots left for this event";
        } catch (IllegalArgumentException e) {
            throw new InvalidInputDataException();
        }
    }

    public String updateFinance(long id) {
        Optional<Finances> finances = financesRepository.findById(id);
        if (finances.isPresent()) {
            finances.get().setAlreadyPaid(!finances.get().getAlreadyPaid());
            try {
                financesRepository.save(finances.get());
                return "Record \"alreadyPaid\" for "+finances.get().getMember().getLogin()+" on event \""
                        +finances.get().getEvent().getEventName()+"\" was successfully changed";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new FinancesNotFoundException();
    }

    public String deleteRecord(long id) {
        Optional<Finances> finance = financesRepository.findById(id);
        if (finance.isPresent()) {
            Optional<Event> event = eventRepository.findById(finance.get().getEvent().getId());
            event.get().setOccupiedSpots(event.get().getOccupiedSpots()-1);
            eventRepository.save(event.get());
            financesRepository.delete(finance.get());
            return "Record for "+finance.get().getMember().getLogin()+" on event \""
                    +finance.get().getEvent().getEventName()+"\" was successfully deleted";
        }
        throw new FinancesNotFoundException();
    }
}
