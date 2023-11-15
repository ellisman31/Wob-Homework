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

    private final String uri = "https://my.api.mockaroo.com/location?key=63304c70";


    public List<Location> getAllLocation() {
        Location[] locations = (Location[]) serviceUtil.restCall(uri, new Location[]{});
        Arrays
                .stream(locations)
                .forEach(locationRepository::save);

        return locationRepository.findAll();
    }

}
