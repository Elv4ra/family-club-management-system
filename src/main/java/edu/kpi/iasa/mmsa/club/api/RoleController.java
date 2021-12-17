package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Role;
import edu.kpi.iasa.mmsa.club.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role newRole) {
        return ResponseEntity.ok(roleService.saveRole(newRole));
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping(value = "/roles/{roleName}")
    public ResponseEntity<List<Role>> findByName(@PathVariable String roleName) {
        return ResponseEntity.ok(roleService.getRoleByName(roleName));
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @Valid @RequestBody Role updatedRole) {
        return ResponseEntity.ok(roleService.updateRole(id, updatedRole));
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        return ResponseEntity.ok(roleService.deleteRoleById(id));
    }
}
