package com.security.practice.service;

import org.apache.ibatis.annotations.Mapper;

import com.security.practice.model.dto.RoleGroupDetails;

@Mapper
public interface RoleGroupDetailsMapper {

	public RoleGroupDetails findByRoleGroupIdAndMenuId(Long roleGroupId ,Long menuId);

}
