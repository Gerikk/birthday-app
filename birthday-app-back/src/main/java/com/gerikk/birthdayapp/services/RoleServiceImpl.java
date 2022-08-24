package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.RoleNotFoundException;
import com.gerikk.birthdayapp.models.Role;
import com.gerikk.birthdayapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findAll().stream()
                .filter(role -> role.getId().equals(roleId)).findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public Role getRoleByUsername(String username) {
        return roleRepository.findAll().stream()
                .filter(role -> role.getName().equals(username)).findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public Role save(Role role) {
        this.roleRepository.save(role);
        return role;
    }
}
