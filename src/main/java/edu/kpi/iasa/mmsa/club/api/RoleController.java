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
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> createRole(@Valid @RequestBody Role newRole) {
        return ResponseEntity.ok(roleService.createRole(newRole));
    }

    @GetMapping
    public ResponseEntity<List<Role>> readAll() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> readById(@PathVariable long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRole(@PathVariable long id, @Valid @RequestBody Role updatedRole) {
        return ResponseEntity.ok(roleService.updateRole(id, updatedRole));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        return ResponseEntity.ok(roleService.deleteRoleById(id));
    }
}
