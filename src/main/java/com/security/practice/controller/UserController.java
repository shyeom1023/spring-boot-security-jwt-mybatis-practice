package com.security.practice.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private UserService userService;

//    @PreAuthorize("hasPermission(#name,'test','write')")
    @ApiOperation(value = "사용자 이름 찾기")
    @PreAuthorize("hasPermission('test/1','download')")
    @GetMapping("/users")
    public String findAllUser(Principal user) {
        return user.getName();
    }
}