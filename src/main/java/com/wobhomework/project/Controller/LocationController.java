package com.wobhomework.project.Controller;

import com.wobhomework.project.Model.Location;
import com.wobhomework.project.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public List<Location> getAllListingStatus() {
        return locationService.getAllLocation();
    }

}
