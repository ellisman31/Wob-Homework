package com.wobhomework.project.Service;

import com.wobhomework.project.Model.Marketplace;
import com.wobhomework.project.Repository.MarketplaceRepository;
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
class MarketplaceServiceTest {

    @InjectMocks
    private MarketplaceService marketplaceService;

    @Mock
    private MarketplaceRepository marketplaceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMarketplaces() {
        List<Marketplace> marketplaces = marketplaceService.getAllMarketplace();

        assertThat(marketplaces).isNotNull();
    }

}