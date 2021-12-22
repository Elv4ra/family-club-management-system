package edu.kpi.iasa.mmsa.club.repository;

import edu.kpi.iasa.mmsa.club.repository.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLogin(String login);
}
