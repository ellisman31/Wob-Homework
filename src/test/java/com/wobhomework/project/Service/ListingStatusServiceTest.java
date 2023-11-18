package com.wobhomework.project.Service;

import com.wobhomework.project.Controller.ListingController;
import com.wobhomework.project.Model.ListingStatus;
import com.wobhomework.project.Repository.ListingStatusRepository;
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
class ListingStatusServiceTest {

    @InjectMocks
    private ListingStatusService listingStatusService;

    @Mock
    private ListingStatusRepository listingStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetListingStatus() {
        List<ListingStatus> listingStatuses = listingStatusService.getAllListingStatus();

        assertThat(listingStatuses).isNotNull();
    }

}