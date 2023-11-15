package com.wobhomework.project.Service;

import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Repository.ListingRepository;
import com.wobhomework.project.Util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ServiceUtil serviceUtil;
    private final ListingRepository listingRepository;
    private final String uri = "https://my.api.mockaroo.com/listing?key=63304c70";

    public List<Listing> getAllListing() {
        Listing[] listings = (Listing[]) serviceUtil.restCall(uri, new Listing[]{});

        Arrays
                .stream(listings)
                .forEach(listingRepository::save);

        return listingRepository.findAll();
    }

    //TODO: Collect all the invalid files into JSON file.
    //TODO: Separate when the database fills up.
}
