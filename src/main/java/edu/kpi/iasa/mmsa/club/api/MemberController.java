package edu.kpi.iasa.mmsa.club.api;


import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import edu.kpi.iasa.mmsa.club.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/members")
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
        Member newMember = new Member(member.getLogin(), member.getPassword(), member.getName(), member.getAlias(), member.getPhone());
        return ResponseEntity.ok(memberService.saveMember(newMember));
    }

    @GetMapping(value ="/members")
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping(value = "/members/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping(value = "/members/login/{login}")
    public ResponseEntity<Member> findMemberByLogin(@PathVariable String login) {
        return ResponseEntity.ok(memberService.getMemberByLogin(login));
    }

    @GetMapping(value = "/members/names/{name}")
    public ResponseEntity<List<Member>> findMembersByName(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getAllMembersByName(name));
    }

    @GetMapping(value = "/members/alias/{alias}")
    public ResponseEntity<List<Member>> findMembersByAlias(@PathVariable String alias) {
        return ResponseEntity.ok(memberService.getAllMembersByAlias(alias));
    }

    @GetMapping(value = "/members/ranks")
    public ResponseEntity<List<Member>> findMembersByRank(@RequestBody Rank rank) {
        return ResponseEntity.ok(memberService.getAllMembersByRank(rank));
    }

    @GetMapping(value = "/members/active/{isActive}")
    public ResponseEntity<List<Member>> findActiveMembers(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(memberService.getAllActiveMembers(isActive));
    }

    @PutMapping(value = "/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member updatedMember) {
        return ResponseEntity.ok(memberService.updateMember(id, updatedMember));
    }

    @DeleteMapping(value = "/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable long id) {
        return ResponseEntity.ok(memberService.deleteMember(id));
    }
}

