package edu.kpi.iasa.mmsa.club.repository;

import edu.kpi.iasa.mmsa.club.repository.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByDate(Date date);

    Optional<Event> findByEventName(String name);
}
