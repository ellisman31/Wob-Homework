package com.wobhomework.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "listing_status")
@JsonIgnoreProperties(value = "listings")
public class ListingStatus {

    @Id
    private int id;
    private String status_name;
    @OneToMany(mappedBy = "listingStatusObject")
    private List<Listing> listings;

}
