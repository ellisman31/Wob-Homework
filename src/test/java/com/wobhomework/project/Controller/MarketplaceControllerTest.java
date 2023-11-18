package com.wobhomework.project.Controller;

import com.wobhomework.project.Service.MarketplaceService;
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
class MarketplaceControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private MarketplaceService marketplaceService;
    @InjectMocks
    private MarketplaceController marketplaceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(marketplaceController).build();
    }

    @Test
    public void testGetAllMarketplace() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/marketplace")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}