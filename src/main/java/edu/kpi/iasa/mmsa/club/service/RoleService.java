package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.RoleAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.RoleRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return  roleRepository.findAll();
    }

    public List<Role> getRoleByName(String roleName) {
        List<Role> roles = roleRepository.findAllByRoleName(roleName);
        if (!(roles.isEmpty())) {
            return roles;
        }
        throw new RoleNotFoundException();
    }

    public Role saveRole(Role newRole) {
        if (!(roleRepository.findByRoleName(newRole.getRoleName()).getClass().getName().equals(newRole.getRoleName()))) {
            return roleRepository.save(newRole);
        }
        throw new RoleAlreadyExistsException();
    }

    public Role updateRole(long id, Role updatedRole) {
        Optional<Role> oldRole = roleRepository.findById(id);
        if (oldRole.isPresent()) {
            oldRole.get().setRoleName(updatedRole.getRoleName());
            return  roleRepository.save(oldRole.get());
        }
        throw  new RoleNotFoundException();
    }

    public String deleteRoleById(long id) {
        roleRepository.deleteById(id);
        return "Role was successfully deleted";
    }
}
