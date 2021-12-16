package com.security.practice.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.service.UserService;

@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private UserService userService;

//    @PreAuthorize("#oauth2.hasScope('write')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasPermission('test','write')")
//    @PreAuthorize("hasPermission(#name,'test','write')")
    @GetMapping("/users")
    public String findAllUser(Principal user) {
        return user.getName();
    }
}