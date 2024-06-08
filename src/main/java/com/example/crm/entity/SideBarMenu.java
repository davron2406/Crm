package com.example.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SideBarMenu {

    @Id
    @GeneratedValue()
    public UUID id;

    @Column(nullable = false,unique = true)
    private  String name;
    private String iconName;
    private String routerLink;

    public SideBarMenu(String name, String iconName, String routerLink){
        this.name = name;
        this.iconName = iconName;
        this.routerLink = routerLink;
    }
}
