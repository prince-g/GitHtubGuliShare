package com.atguigu.tingshu.security.service;

import com.atguigu.tingshu.system.client.SecurityLoginFeignClient;
import com.atguigu.tingshu.common.result.Result;
import com.atguigu.tingshu.model.system.SysUser;
import com.atguigu.tingshu.security.custom.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;


@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityLoginFeignClient securityLoginFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<SysUser> sysUserResult = securityLoginFeignClient.getByUsername(username);
        Assert.notNull(sysUserResult);
        SysUser sysUser = sysUserResult.getData();
        Assert.notNull(sysUser);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if(!"admin".equals(sysUser.getUsername()) && sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("账号已停用");
        }
        Result<List<String>> userPermsListResult = securityLoginFeignClient.findUserPermsList(sysUser.getId());
        Assert.notNull(userPermsListResult);
        List<String> userPermsList = userPermsListResult.getData();
        Assert.notNull(userPermsList);
        sysUser.setUserPermsList(userPermsList);
        return new CustomUser(sysUser);
    }
}