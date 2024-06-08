package com.example.crm.repository;


import com.example.crm.entity.SideBarMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SideBarMenuRepository extends JpaRepository<SideBarMenu, UUID> {

    SideBarMenu findByName(String name);
}
