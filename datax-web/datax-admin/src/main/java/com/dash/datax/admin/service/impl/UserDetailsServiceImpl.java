package com.dash.datax.admin.service.impl;

import com.dash.datax.admin.mapper.JobUserMapper;
import com.dash.datax.admin.entity.JobUser;
import com.dash.datax.admin.entity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl
 * @author jingwk
 * @since 2019-03-15
 * @version v2.1.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JobUserMapper jobUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        JobUser user = jobUserMapper.loadByUserName(s);
        return new JwtUser(user);
    }

}
