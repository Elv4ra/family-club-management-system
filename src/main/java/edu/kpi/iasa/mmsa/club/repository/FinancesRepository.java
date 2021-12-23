package edu.kpi.iasa.mmsa.club.repository;

import edu.kpi.iasa.mmsa.club.repository.model.Finances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancesRepository extends JpaRepository<Finances, Long> {
}
