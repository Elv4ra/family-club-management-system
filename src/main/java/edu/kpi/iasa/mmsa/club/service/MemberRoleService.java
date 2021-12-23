package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
import edu.kpi.iasa.mmsa.club.exception.MemberRoleAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.MemberRoleNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.MemberRoleRepository;
import edu.kpi.iasa.mmsa.club.repository.model.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public MemberRoleService(MemberRoleRepository memberRoleRepository) {
        this.memberRoleRepository = memberRoleRepository;
    }

    public String createMemberRole(MemberRole memberRole) {
        List<MemberRole> memberRoles = memberRoleRepository.findAll();
        for (MemberRole savedMemberRole : memberRoles) {
            if (savedMemberRole.getRole().getId() == memberRole.getRole().getId()
            && savedMemberRole.getMember().getId() == memberRole.getMember().getId()) {
                throw new MemberRoleAlreadyExistsException();
            }
        }
        try {
            memberRoleRepository.save(memberRole);
            return "New Member Role "+memberRole.getRole().getRoleName()+" for "+memberRole.getMember().getLogin()+" was successfully created";
        } catch (IllegalArgumentException e) {
            throw new InvalidInputDataException();
        }
    }

    public List<MemberRole> getAllMemberRoles() {
        List<MemberRole> membersRoles = memberRoleRepository.findAll();
        if (membersRoles.isEmpty()) {
            throw new MemberRoleNotFoundException();
        }
        return membersRoles;
    }

    public List<MemberRole> getAllMembersRoles(String name) {
        List<MemberRole> membersRoles = memberRoleRepository.findAll(), result = new ArrayList<>();
        for (MemberRole memberRole : membersRoles) {
            if (memberRole.getMember().getLogin().equals(name)
            || memberRole.getMember().getName().equals(name)
            || memberRole.getMember().getAlias().equals(name)) {
                result.add(memberRole);
            }
        }
        if (!(result.isEmpty())) {
            return result;
        }
        throw new MemberRoleNotFoundException();
    }

    public List<MemberRole> getAllRolesMembers(String roleName) {
        List<MemberRole> rolesMembers = memberRoleRepository.findAll(), result = new ArrayList<>();
        for (MemberRole memberRole : rolesMembers) {
            if (memberRole.getRole().getRoleName().equals(roleName)) {
                result.add(memberRole);
            }
        }
        if (!(result.isEmpty())) {
            return result;
        }
        throw new MemberRoleNotFoundException();
    }

    public String deleteMembersRole(long id) {
        Optional<MemberRole> memberRole = memberRoleRepository.findById(id);
        if (memberRole.isPresent()) {
            memberRoleRepository.delete(memberRole.get());
            return "Role " + memberRole.get().getRole().getRoleName() + " for member " + memberRole.get().getMember().getLogin() + " was successfully deleted";
        }
        throw new MemberRoleNotFoundException();
    }
}
