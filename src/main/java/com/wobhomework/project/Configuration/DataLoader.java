package com.wobhomework.project.Configuration;

import com.wobhomework.project.Model.*;
import com.wobhomework.project.Repository.*;
import com.wobhomework.project.Util.ConvertDataIntoCSVFile;
import com.wobhomework.project.Util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final ServiceUtil serviceUtil;
    private final String listingUri = "https://my.api.mockaroo.com/listing?key=63304c70";
    private final String listingStatusUri = "https://my.api.mockaroo.com/listingStatus?key=63304c70";
    private final String marketplaceUri = "https://my.api.mockaroo.com/marketplace?key=63304c70";
    private final String locationUri = "https://my.api.mockaroo.com/location?key=63304c70";
    private final ListingRepository listingRepository;
    private final ListingStatusRepository listingStatusRepository;
    private final MarketplaceRepository marketplaceRepository;
    private final LocationRepository locationRepository;
    private final ConvertDataIntoCSVFile convertDataIntoCSVFile;


    public void run(ApplicationArguments args) throws IOException {
        dataInitialize(listingStatusRepository,listingStatusUri, new ListingStatus[]{});
        dataInitialize(marketplaceRepository,marketplaceUri, new Marketplace[]{});
        dataInitialize(locationRepository,locationUri, new Location[]{});
        dataInitialize(listingRepository,listingUri, new Listing[]{});
        convertDataIntoCSVFile.convertGivenDataToCSVForListing();
    }

    private void dataInitialize(JpaRepository jpaRepository, String uri, Object[] object) {
        object = (Object[]) serviceUtil.restCall(uri, object);

        Arrays
                .stream(object)
                .forEach(jpaRepository::save);
    }

}
