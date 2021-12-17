package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.RoleRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return  roleRepository.findAll();
    }
}
