package com.security.practice.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.security.practice.model.dto.Menu;
import com.security.practice.model.dto.RoleGroupDetails;
import com.security.practice.model.dto.User;
import com.security.practice.service.MenuService;
import com.security.practice.service.RoleGroupDetailsService;
import com.security.practice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientPermissionExpression implements PermissionEvaluator {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleGroupDetailsService roleGroupDetailsService;

	boolean authorizationVerification = false;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 적절한 권한 관리 코드

    	log.info("authentication => " + authentication.getName());
    	User user = userService.findByPk(authentication.getName());
    	Menu menu = menuService.findByMenuPath(targetDomainObject.toString());
    	RoleGroupDetails roleGroupDetails = roleGroupDetailsService.findByRoleGroupIdAndMenuId(user.getRoleGroupId(), menu.getMenuId());
    	List<String> scope = Arrays.asList(roleGroupDetails.getScope().split(","));
    	scope.forEach(x -> {
    		if(permission.toString().equals(x)) {
    			authorizationVerification = true;
    		}
    	});
    	log.info("targetDomainObject => " + targetDomainObject.toString());
    	log.info("permission :" + permission.toString());
    	log.info("roleGroupDetails => " + roleGroupDetails.getScope());
    	log.info("비교 => " + authorizationVerification);


        return authorizationVerification;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // 적절한 권한 관리 코드
    	log.info("targetId :" + targetType);
        return true;
    }
}