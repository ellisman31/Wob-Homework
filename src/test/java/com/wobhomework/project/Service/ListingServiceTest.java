package com.wobhomework.project.Service;

import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Repository.ListingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListingServiceTest {

    @InjectMocks
    private ListingService listingService;
    @Mock
    private ListingRepository listingRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllListing() {
        List<Listing> listings = listingService.getAllListing();

        assertThat(listings).isNotNull();
        assertThat(listings.size()).isEqualTo(1000);
    }

}