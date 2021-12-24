package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.MemberRole;
import edu.kpi.iasa.mmsa.club.service.MemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/membersroles")
public class MemberRoleController {

    private final MemberRoleService memberRoleService;

    @Autowired
    public MemberRoleController(MemberRoleService memberRoleService) {
        this.memberRoleService = memberRoleService;
    }

    @PostMapping
    public ResponseEntity<String> createMembersRole(@RequestBody MemberRole memberRole) {
        return ResponseEntity.ok(memberRoleService.createMemberRole(memberRole));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMembersRole(@PathVariable long id) {
        return ResponseEntity.ok(memberRoleService.deleteMembersRole(id));
    }
}
