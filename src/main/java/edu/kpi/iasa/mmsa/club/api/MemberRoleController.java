package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.MemberRole;
import edu.kpi.iasa.mmsa.club.repository.model.Role;
import edu.kpi.iasa.mmsa.club.service.MemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberRoleController {

    private final MemberRoleService memberRoleService;

    @Autowired
    public MemberRoleController(MemberRoleService memberRoleService) {
        this.memberRoleService = memberRoleService;
    }

    @PostMapping(value = "/membersroles")
    public ResponseEntity<String> createMembersRole(@Valid @RequestBody MemberRole memberRole) {
        return ResponseEntity.ok(memberRoleService.createMemberRole(memberRole));
    }

    @GetMapping(value = "/membersroles")
    public ResponseEntity<List<MemberRole>> readAll() {
        return ResponseEntity.ok(memberRoleService.getAllMemberRoles());
    }

    @GetMapping(value = "/membersroles/member/{memberName}")
    public ResponseEntity<List<MemberRole>> readAllMembersRoles(@PathVariable String memberName) {
        return ResponseEntity.ok(memberRoleService.getAllMembersRoles(memberName));
    }

    @GetMapping(value = "/membersroles/role/{roleName}")
    public ResponseEntity<List<MemberRole>> readAllRolesMembers(@PathVariable String roleName) {
        return ResponseEntity.ok(memberRoleService.getAllRolesMembers(roleName));
    }

    @DeleteMapping(value = "/membersroles/{id}")
    public ResponseEntity<String> deleteMembersRole(@PathVariable long id) {
        return ResponseEntity.ok(memberRoleService.deleteMembersRole(id));
    }
}
