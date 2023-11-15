package com.wobhomework.project.Service;

import com.wobhomework.project.Model.Marketplace;
import com.wobhomework.project.Repository.MarketplaceRepository;
import com.wobhomework.project.Util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketplaceService {

    private final ServiceUtil serviceUtil;
    private final MarketplaceRepository marketplaceRepository;

    private final String uri = "https://my.api.mockaroo.com/marketplace?key=63304c70";


    public List<Marketplace> getAllMarketplace() {
        Marketplace[] marketplaces = (Marketplace[]) serviceUtil.restCall(uri, new Marketplace[]{});
        Arrays
                .stream(marketplaces)
                .forEach(marketplaceRepository::save);

        return marketplaceRepository.findAll();
    }
}
