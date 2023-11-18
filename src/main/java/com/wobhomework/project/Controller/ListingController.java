package com.wobhomework.project.Controller;

import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public List<Listing> getAllListing() {
        return listingService.getAllListing();
    }


}
