package com.xg.supermarket.service.impl;

import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.RoleMapper;
import com.xg.supermarket.pojo.Role;
import com.xg.supermarket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int addRole(Role role) {
        regRole(role,false);
        return roleMapper.insertSelective(role);
    }

    @Override
    public int updateRole(Role role) {
        regRole(role,true);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delRole(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    private void regRole(Role role,boolean flag){
        if(role == null){
            throw new BizException("请检查角色信息");
        }
        if(flag && role.getRid()==null){
            throw new BizException("角色参数错误，请检查角色ID");
        }
        if(StringUtil.isEmpty(role.getRname())){
            throw new BizException("角色参数错误，请检查角色名称");
        }
        if(StringUtil.isEmpty(role.getRno())){
            throw new BizException("角色参数错误，请检查角色编号");
        }
    }
}
