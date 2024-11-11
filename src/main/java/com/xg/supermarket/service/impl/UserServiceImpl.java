package com.xg.supermarket.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xg.supermarket.exception.BizException;
import com.xg.supermarket.mapper.UserMapper;
import com.xg.supermarket.pojo.User;
import com.xg.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> pageUserBy(Integer pageNum, Integer pageSize, String name, String uno, String tel) {
        Example example = new Example(User.class);
        example.setOrderByClause("`status` desc");
        Example.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(name)){
            criteria.andLike("realName","%"+name+"%");
        }
        if(StringUtil.isNotEmpty(uno)){
            criteria.andEqualTo("uno",uno);
        }
        if(StringUtil.isNotEmpty(tel)){
            criteria.andEqualTo("tel",tel);
        }
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(userMapper.selectByExample(example));
    }

    @Override
    public int addUser(User user) {
        //验证
        regUser(user,false);
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        regUser(user,true);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delUser(Integer uid) {
        User user = new User();
        user.setUid(uid);
        user.setStatus(0);

        return userMapper.updateByPrimaryKeySelective(user);
    }

    private void regUser(User user,boolean falg){

        if(user == null){
            throw new BizException("请检查用户数据");
        }
        if(falg && user.getUid()==null){
            throw new BizException("请检查参数，无用户ID");
        }

        if(StringUtil.isEmpty(user.getRealName())){
            throw new BizException("请检查参数，无用户姓名");
        }
        if(Pattern.matches("/^[a-zA-Z0-9_-]{4,16}$/",user.getUsername())){
            throw new BizException("请检查参数，用户名不符合要求");
        }
        if(Pattern.matches("/^[a-zA-Z0-9_]{6,16}$/",user.getPassword())){
            throw new BizException("请检查参数，密码不符合要求");
        }
        if(Pattern.matches("/^1[0-9]{10}$/",user.getTel())){
            throw new BizException("请检查参数，手机不符合要求");
        }
    }
}
