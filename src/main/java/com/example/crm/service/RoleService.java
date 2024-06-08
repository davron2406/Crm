package com.example.crm.service;


import com.example.crm.entity.Role;
import com.example.crm.entity.enums.Permission;
import com.example.crm.payload.ApiResponse;
import com.example.crm.payload.RoleDto;
import com.example.crm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;



    public ApiResponse addRole(RoleDto roleDto){
         Optional<Role> optionalRole = roleRepository.findByName(roleDto.getName());
        if(optionalRole.isPresent())
            return new ApiResponse("Role Already exist", false);

        List<Permission> permissions = new ArrayList<>();

        roleDto.getPermissions().forEach(permission -> {
            permissions.add(Permission.valueOf(permission));
        });
        Role role = new Role(roleDto.getName(), permissions);
        roleRepository.save(role);

        return  new ApiResponse("Role successfully added",true);
    }

    public Page<Role> searchRole(String roleName, Pageable pageable) {
        return roleRepository.searchRole(roleName,pageable);
    }

    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public List<String> getPermissions() {
        Permission[] permission = Permission.values();
        List<String> permissions = new ArrayList<>();
        for(int i = 0; i < permission.length; i++) {
            permissions.add(permission[i].name());
        }

        return permissions;
    }
}
