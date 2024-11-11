package com.xg.supermarket.service;

import com.xg.supermarket.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> listAll();
    int addRole(Role role);
    int updateRole(Role role);
    int delRole(Integer rid);
}
