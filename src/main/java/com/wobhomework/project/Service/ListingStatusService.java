package com.wobhomework.project.Service;

import com.wobhomework.project.Model.ListingStatus;
import com.wobhomework.project.Model.Location;
import com.wobhomework.project.Repository.ListingStatusRepository;
import com.wobhomework.project.Util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingStatusService {

    private final ServiceUtil serviceUtil;
    private final ListingStatusRepository listingStatusRepository;

    private final String uri = "https://my.api.mockaroo.com/listingStatus?key=63304c70";


    public List<ListingStatus> getAllListingStatus() {
        ListingStatus[] listingStatuses = (ListingStatus[]) serviceUtil.restCall(uri, new ListingStatus[]{});
        Arrays
                .stream(listingStatuses)
                .forEach(listingStatusRepository::save);

        return listingStatusRepository.findAll();
    }

}
