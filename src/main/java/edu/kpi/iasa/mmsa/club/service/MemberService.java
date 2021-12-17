package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.MemberRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
