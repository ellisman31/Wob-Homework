package com.wobhomework.project.Service;

import com.wobhomework.project.Model.Location;
import com.wobhomework.project.Model.Marketplace;
import com.wobhomework.project.Repository.LocationRepository;
import com.wobhomework.project.Repository.MarketplaceRepository;
import com.wobhomework.project.Util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final ServiceUtil serviceUtil;
    private final LocationRepository locationRepository;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

}
