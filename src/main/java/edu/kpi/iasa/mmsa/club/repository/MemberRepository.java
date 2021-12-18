package edu.kpi.iasa.mmsa.club.repository;

import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLogin(String login);

    List<Member> findAllByName(String name);

    List<Member> findAllByAlias(String alias);

    List<Member> findAllByRank(Rank rank);

    List<Member> findAllByIsActiveMember(Boolean isActive);
}
