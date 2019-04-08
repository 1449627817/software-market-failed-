package com.sicau.dao;

import com.sicau.entity.dto.IdentityAndAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdentityAndAuthorityMapper {
    int insert(IdentityAndAuthority record);

    int insertSelective(IdentityAndAuthority record);
    List<String> getAuthorityId(@Param("identityId") String identityId);
}