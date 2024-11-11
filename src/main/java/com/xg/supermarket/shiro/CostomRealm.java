package com.xg.supermarket.shiro;

import com.xg.supermarket.mapper.PermsMapper;
import com.xg.supermarket.mapper.RoleMapper;
import com.xg.supermarket.mapper.UserMapper;
import com.xg.supermarket.pojo.Perms;
import com.xg.supermarket.pojo.Role;
import com.xg.supermarket.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName CostomRealm.java
 * @Description 自定义Realm
 * @createTime 2021年07月01日 08:52:00
 */

public class CostomRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermsMapper permsMapper;
    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        info.addRole(user.getRole().getRno());
        List<Perms> perms = user.getRole().getPerms();
        List<String> list = perms.stream().map(Perms::getPname).collect(Collectors.toList());
        info.addStringPermissions(list);
        return info;
    }
    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取前台传过来得用户名
        Object username = authenticationToken.getPrincipal();
        //查用户名
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        User user1 = userMapper.selectOneByExample(example);
        //获取角色
        Role role = roleMapper.selectByPrimaryKey(user1.getRid());
        //获取权限
         System.err.println(role==null);
         List<Perms> perms = permsMapper.listByRID(role.getRid());
        role.setPerms(perms);
        user1.setRole(role);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user1,user1.getPassword(),getName());
        return authenticationInfo;
    }
}
