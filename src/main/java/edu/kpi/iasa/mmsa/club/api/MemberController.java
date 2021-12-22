package edu.kpi.iasa.mmsa.club.api;


import edu.kpi.iasa.mmsa.club.repository.model.Member;
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
    public ResponseEntity<String> createMember(@Valid @RequestBody Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @GetMapping(value ="/members")
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping(value = "/members/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping(value = "/members/name/{name}")
    public ResponseEntity<Member> findMemberByLogin(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getMemberByName(name));
    }

    @GetMapping(value = "/members/names/{name}")
    public ResponseEntity<List<Member>> findMembersByName(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getAllMembersByName(name));
    }

    @GetMapping(value = "/members/ranks/{id}")
    public ResponseEntity<List<Member>> findMembersByRank(@PathVariable long id) {
        return ResponseEntity.ok(memberService.getAllMembersByRank(id));
    }

    @GetMapping(value = "/members/active/{isActive}")
    public ResponseEntity<List<Member>> findActiveMembers(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(memberService.getAllActiveMembers(isActive));
    }

    @PutMapping(value = "/members/{id}")
    public ResponseEntity<String> updateMember(@PathVariable long id, @RequestBody Member updatedMember) {
        return ResponseEntity.ok(memberService.updateMember(id, updatedMember));
    }

    @DeleteMapping(value = "/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable long id) {
        return ResponseEntity.ok(memberService.deleteMember(id));
    }
}

