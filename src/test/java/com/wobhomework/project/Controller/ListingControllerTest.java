package com.wobhomework.project.Controller;

import com.wobhomework.project.Service.ListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ListingControllerTest {

    private MockMvc mvc;
    @Mock
    private ListingService listingService;
    @InjectMocks
    private ListingController listingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(listingController).build();
    }

    @Test
    public void testGetListing() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/listing")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}