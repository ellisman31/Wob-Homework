package com.wobhomework.project.Controller;

import com.wobhomework.project.Model.ListingStatus;
import com.wobhomework.project.Service.ListingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listingStatus")
public class ListingStatusController {

    private final ListingStatusService listingStatusService;

    @GetMapping
    public List<ListingStatus> getAllListingStatus() {
        return listingStatusService.getAllListingStatus();
    }

}
