package com.wobhomework.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    private UUID id;
    private String manager_name;
    private String phone;
    private String address_primary;
    private String address_secondary;
    private String country;
    private String town;
    private String postal_code;

}
