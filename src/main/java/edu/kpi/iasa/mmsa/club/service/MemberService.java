package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.MemberAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.MemberNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.MemberRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        }
        throw new MemberNotFoundException();
    }

    public Member getMemberByName(String name) {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if (member.getLogin().equals(name)
            || member.getName().equals(name)
            || member.getAlias().equals(name)) {
                return member;
            }
        }
        throw new MemberNotFoundException();
    }

    public List<Member> getAllMembersByName(String name) {
        List<Member> members = memberRepository.findAll(), result = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().equals(name)
            || member.getAlias().equals(name)) {
                result.add(member);
            }
        }
        if (result.isEmpty()) {
            throw new MemberNotFoundException();
        }
        return result;
    }

    public List<Member> getAllMembersByRank(long rankId) {
        List<Member> members = memberRepository.findAll(), result = new ArrayList<>();
        for (Member member : members) {
            if (member.getMemberRank().getId() == rankId) {
                result.add(member);
            }
        }
        if (result.isEmpty()) {
            throw new MemberNotFoundException();
        }
        return result;
    }

    public List<Member> getAllActiveMembers(Boolean isActive) {
        List<Member> members = memberRepository.findAll(), result = new ArrayList<>();
        for (Member member : members) {
            if (member.getIsActiveMember()) {
                result.add(member);
            }
        }
        if (result.isEmpty()) {
            throw new MemberNotFoundException();
        }
        return result;
    }

    public Member saveMember(Member newMember) {
        if (!(memberRepository.findByLogin((newMember.getLogin())).isPresent())) {
            return memberRepository.save(newMember);
        }
        throw new MemberAlreadyExistsException();
    }

    public Member updateMember(long id, Member updatedMember) {
        Optional<Member> oldMember = memberRepository.findById(id);
        if (oldMember.isPresent()) {
            updating(oldMember.get(), updatedMember);
            return memberRepository.save(oldMember.get());
        }
        throw new MemberNotFoundException();
    }

    private void updating(Member oldMember, Member newMember) {
        if (memberRepository.findByLogin(newMember.getLogin()).isPresent()) {
            throw new MemberAlreadyExistsException();
        }
        if (newMember.getLogin() != null && !(newMember.getLogin().isBlank())) oldMember.setLogin(newMember.getLogin());
        if (newMember.getPassword() != null && !(newMember.getPassword().isBlank())) oldMember.setPassword(newMember.getPassword());
        if (newMember.getName() != null && !(newMember.getName().isBlank())) oldMember.setName(newMember.getName());
        if (newMember.getAlias() != null && !(newMember.getAlias().isBlank())) oldMember.setAlias(newMember.getAlias());
        if (newMember.getPhone() != null && !(newMember.getPhone().isBlank())) oldMember.setPhone(newMember.getPhone());
        if (newMember.getJoiningDate() != null && newMember.getIsActiveMember().booleanValue()) oldMember.setIsActiveMember(newMember.getIsActiveMember());
        if (newMember.getJoiningDate() != null) oldMember.setJoiningDate(newMember.getJoiningDate());

        if (newMember.getMemberRank() != null) oldMember.setMemberRank(newMember.getMemberRank());
    }

    public String deleteMember(long id) {
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
            return "Member was successfully deleted";
        }
        throw new MemberNotFoundException();
    }
}
