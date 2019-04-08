package com.sicau.dao;

import com.sicau.entity.dto.Identity;
import org.apache.ibatis.annotations.Param;

public interface IdentityMapper {
    int insert(Identity record);

    int insertSelective(Identity record);
    Identity getIdentity(@Param("identityId") String identityId);
}