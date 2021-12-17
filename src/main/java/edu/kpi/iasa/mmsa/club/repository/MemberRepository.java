package edu.kpi.iasa.mmsa.club.repository;

import edu.kpi.iasa.mmsa.club.repository.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
