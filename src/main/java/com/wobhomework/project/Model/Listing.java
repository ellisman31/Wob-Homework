package com.wobhomework.project.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "listing")
public class Listing {

    @Id
    private UUID id;
    private String title;
    private String description;
    private UUID location_id;
    private BigDecimal listing_price;
    private String currency;
    private int quantity;
    private int listing_status;
    private int marketplace;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date upload_time;
    private String owner_email_address;

}
