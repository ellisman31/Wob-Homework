package com.wobhomework.project.Controller;

import com.wobhomework.project.Service.ListingStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ListingStatusControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private ListingStatusService listingStatusService;
    @InjectMocks
    private ListingStatusController listingStatusController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(listingStatusController).build();
    }
    @Test
    public void testGetListingStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/listingStatus")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}