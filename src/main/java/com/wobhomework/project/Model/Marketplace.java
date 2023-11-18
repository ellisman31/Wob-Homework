package com.wobhomework.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marketplace")
@JsonIgnoreProperties(value = "listings")
public class Marketplace {

    @Id
    private int id;
    private String marketplace_name;
    @OneToMany(mappedBy = "marketplaceObject")
    private List<Listing> listings;

}
