package com.security.practice.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.security.practice.model.dto.Menu;
import com.security.practice.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientPermissionExpression implements PermissionEvaluator {

	@Autowired
	private MenuService menuService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 적절한 권한 관리 코드
//    	log.info("authentication :" + authentication.getDetails().toString());
    	log.info("targetDomainObject :" + targetDomainObject.toString());
//    	log.info("permission :" + permission.toString());
//    	Menu findByNmae = menuService.findByName(targetDomainObject.toString());
    	Menu findByNmae = menuService.findByName(targetDomainObject.toString());
    	log.info("findByNmae :" + findByNmae.getName());
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // 적절한 권한 관리 코드
    	log.info("targetId :" + targetType);
//    	Menu findByNmae = menuService.findByName(targetType);
//    	log.info("findByNmae :" + findByNmae.getName());
        return true;
    }
}