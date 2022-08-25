package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.RoleNotFoundException;
import com.gerikk.birthdayapp.models.Role;
import com.gerikk.birthdayapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return this.roleRepository.findAll();
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findAll().stream()
                .filter(role -> role.getId().equals(roleId)).findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }

    public Role getRoleByUsername(String username) {
        return roleRepository.findAll().stream()
                .filter(role -> role.getName().equals(username)).findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }

    public Role save(Role role) {
        this.roleRepository.save(role);
        return role;
    }
}
