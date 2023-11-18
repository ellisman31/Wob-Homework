package com.wobhomework.project.Controller;

import com.wobhomework.project.Model.Marketplace;
import com.wobhomework.project.Service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marketplace")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    @GetMapping
    public List<Marketplace> getAllMarketplace() {
        return marketplaceService.getAllMarketplace();
    }

}
