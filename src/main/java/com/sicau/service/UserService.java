package com.sicau.service;

import com.sicau.entity.dto.Authority;
import com.sicau.entity.dto.Identity;

import java.util.List;

/**
 * @program: software-market
 * @description:
 * @author: Lee
 * @create: 2019-02-20 23:16
 **/
public interface UserService {
/**
 * @param userId
 * @return Identity
 * @author Lee
 * @description :获取用户身份
 * @date 2019/2/21
 */
    Identity getIdentity(String userId);
    /**
     * @param identityId
     * @return java.util.List<com.sicau.entity.dto.Authority>
     * @author Lee
     * @description :获取用户的能执行的操作
     * @date 2019/2/21
     */
    List<Authority> getAuthority(String identityId);
}
