package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleById(Long roleId);

    public Role getRoleByUsername(String username);

    public Role save(Role role);

}
