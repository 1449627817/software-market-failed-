package com.sicau.dao;

import com.sicau.entity.dto.Authority;

import java.util.List;

public interface AuthorityMapper {
    int insert(Authority record);

    int insertSelective(Authority record);
    List<Authority> getAuthotity(List<String> authorityId);
}