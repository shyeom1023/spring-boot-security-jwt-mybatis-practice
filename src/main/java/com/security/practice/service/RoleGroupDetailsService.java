package com.security.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.practice.model.dto.RoleGroupDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleGroupDetailsService implements RoleGroupDetailsMapper {

	@Autowired
	private RoleGroupDetailsMapper roleGroupDetailsMapper;

	@Override
	public RoleGroupDetails findByRoleGroupIdAndMenuId(Long roleGroupId ,Long menuId) {
		// TODO Auto-generated method stub
		return roleGroupDetailsMapper.findByRoleGroupIdAndMenuId(roleGroupId,menuId);
	}

}
