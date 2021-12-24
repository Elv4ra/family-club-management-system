package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.repository.model.MemberRole;
import edu.kpi.iasa.mmsa.club.service.MemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public ResponseEntity<List<MemberRole>> findAll() {
        return ResponseEntity.ok(memberRoleService.getAllMemberRoles());
    }

    @GetMapping(value = "/member/{memberName}")
    public ResponseEntity<List<MemberRole>> findAllByMember(@PathVariable String memberName) {
        return ResponseEntity.ok(memberRoleService.getAllMembersRoles(memberName));
    }

    @GetMapping(value = "/role/{roleName}")
    public ResponseEntity<List<MemberRole>> findAllByRole(@PathVariable String roleName) {
        return ResponseEntity.ok(memberRoleService.getAllRolesMembers(roleName));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMembersRole(@PathVariable long id) {
        return ResponseEntity.ok(memberRoleService.deleteMembersRole(id));
    }
}
