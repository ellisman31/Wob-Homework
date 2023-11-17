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

    public List<Listing> getAllListing() {
        return listingRepository.findAll();
    }

}
