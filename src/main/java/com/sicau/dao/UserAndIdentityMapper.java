package com.sicau.dao;

import com.sicau.entity.dto.UserAndIdentity;
import org.apache.ibatis.annotations.Param;

public interface UserAndIdentityMapper {
    int insert(UserAndIdentity record);

    int insertSelective(UserAndIdentity record);
    String getIdentityId(@Param("userId") String userId);
}