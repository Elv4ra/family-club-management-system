package edu.kpi.iasa.mmsa.club.api;


import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> readAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Member> readMemberById(@PathVariable long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Member> readMemberByLogin(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getMemberByName(name));
    }

    @GetMapping(value = "/names/{name}")
    public ResponseEntity<List<Member>> readMembersByName(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getAllMembersByName(name));
    }

    @GetMapping(value = "/ranks/{id}")
    public ResponseEntity<List<Member>> readMembersByRank(@PathVariable long id) {
        return ResponseEntity.ok(memberService.getAllMembersByRank(id));
    }

    @GetMapping(value = "/active/{isActive}")
    public ResponseEntity<List<Member>> readActiveMembers(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(memberService.getAllActiveMembers(isActive));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateMember(@PathVariable long id, @RequestBody Member updatedMember) {
        return ResponseEntity.ok(memberService.updateMember(id, updatedMember));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable long id) {
        return ResponseEntity.ok(memberService.deleteMember(id));
    }
}

