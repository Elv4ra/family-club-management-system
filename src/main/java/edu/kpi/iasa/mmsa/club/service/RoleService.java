package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
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
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new RoleNotFoundException();
        }
        return  roles;
    }

    public Role getRoleById(long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        throw new RoleNotFoundException();
    }

    public String createRole(Role newRole) {
        if (!(roleRepository.findByRoleName(newRole.getRoleName()).getClass().getName().equals(newRole.getRoleName()))) {
            try {
                roleRepository.save(newRole);
                return "Role was successfully created";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new RoleAlreadyExistsException();
    }

    public String updateRole(long id, Role updatedRole) {
        Optional<Role> oldRole = roleRepository.findById(id);
        if (oldRole.isPresent()) {
            try {
                oldRole.get().setRoleName(updatedRole.getRoleName());
                roleRepository.save(oldRole.get());
                return "Role with id="+String.valueOf(id)+"was successfully updated";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw  new RoleNotFoundException();
    }

    public String deleteRoleById(long id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
            return "Role with id="+String.valueOf(id)+"was successfully deleted";
        }
        throw new RoleNotFoundException();
    }
}
