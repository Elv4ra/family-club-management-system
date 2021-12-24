package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
import edu.kpi.iasa.mmsa.club.exception.RoleAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.RoleRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public String createRole(Role newRole) {
        if (!(roleRepository.findByRoleName(newRole.getRoleName()).isPresent())) {
            try {
                roleRepository.save(newRole);
                return "Role was successfully created";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new RoleAlreadyExistsException();
    }

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

    public String updateRole(long id, Role updatedRole) {
        Optional<Role> oldRole = roleRepository.findById(id);
        if (oldRole.isPresent()) {
            try {
                oldRole.get().setRoleName(updatedRole.getRoleName());
                roleRepository.save(oldRole.get());
                return "Role with id="+String.valueOf(id)+" was successfully updated";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw  new RoleNotFoundException();
    }

    public String deleteRoleById(long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return "Role "+roleRepository.findById(id).get().getRoleName()+" was successfully deleted";
        }
        throw new RoleNotFoundException();
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findRoleByCode(code).orElseThrow(RoleNotFoundException::new);
    }
}
