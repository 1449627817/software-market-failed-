package com.sicau.service.impl;

import com.sicau.dao.AuthorityMapper;
import com.sicau.dao.IdentityAndAuthorityMapper;
import com.sicau.dao.IdentityMapper;
import com.sicau.dao.UserAndIdentityMapper;
import com.sicau.entity.dto.Authority;
import com.sicau.entity.dto.Identity;
import com.sicau.entity.dto.IdentityAndAuthority;
import com.sicau.entity.dto.UserAndIdentity;
import com.sicau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: software-market
 * @description:
 * @author: Lee
 * @create: 2019-02-20 23:16
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AuthorityMapper authorityMapper;
    @Autowired
    IdentityMapper identityMapper;
    @Autowired
    IdentityAndAuthorityMapper identityAndAuthorityMapper;
    @Autowired
    UserAndIdentityMapper userAndIdentityMapper;
    @Override
    @Transactional
    public Identity getIdentity(String userId) {
        String identityId=userAndIdentityMapper.getIdentityId(userId);
        Identity identity=identityMapper.getIdentity(identityId);
        return identity;
    }
    @Override
    @Transactional
    public List<Authority> getAuthority(String identityId) {
        List<String> authorityIdList=identityAndAuthorityMapper.getAuthorityId(identityId);
        List<Authority> authorityList=authorityMapper.getAuthotity(authorityIdList);
        return authorityList;
    }
}
