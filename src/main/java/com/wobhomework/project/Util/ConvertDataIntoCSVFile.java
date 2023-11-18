package com.wobhomework.project.Util;

import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Repository.ListingRepository;
import com.wobhomework.project.Repository.MarketplaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConvertDataIntoCSVFile {

    private static final String CSV_FILE_NAME = "importLog";
    private static final String CSV_EXTENSION = ".csv";
    private final ListingRepository listingRepository;
    private final MarketplaceRepository marketplaceRepository;

    public void convertGivenDataToCSVForListing() throws IOException {
        List<Listing> listings = listingRepository.findAll();
        List<String[]> csvData = createCsvDataForListing(listings);

        File csvOutputFile = new File(CSV_FILE_NAME + CSV_EXTENSION);
        FileWriter fileWriter = new FileWriter(csvOutputFile);
        for (String[] data : csvData) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append("\"");
                line.append(data[i].replaceAll("\"","\"\""));
                line.append("\"");
                if (i != data.length - 1) {
                    line.append(';');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
    }

    private List<String[]> createCsvDataForListing(List<Listing> listings) {
        String[] header = {"ListingId", "MarketplaceName", "InvalidField"};

        List<String[]> list = new ArrayList<>();
        list.add(header);

        List<String> importLogBodyList = new ArrayList<>();
        listings.forEach(listing -> {
            importLogBodyListValidation(listing, importLogBodyList);
            list.add(importLogBodyList.toArray(String[]::new));
            importLogBodyList.clear();
        });

        return list;
    }

    private void importLogBodyListValidation(Listing listing, List<String> importLogBodyList) {
        if (listingRepository.findById(listing.getId()).isPresent()) {
            UUID listingId = listing.getId();
            importLogBodyList.add(String.valueOf(listingId));

            if (listingRepository.findMarketplaceByListingId(listingId) != 0) {
                int marketplaceId = listingRepository.findMarketplaceByListingId(listingId);
                if (marketplaceRepository.findById(marketplaceId).isPresent()) {
                    String marketplaceName = marketplaceRepository.findById(marketplaceId).get().getMarketplace_name();
                    importLogBodyList.add(marketplaceName);
                }
            } else {
                importLogBodyList.add("");
                importLogBodyList.add("marketplace");
            }

            if (listingRepository.findTitleByListingId(listingId) == null) {
                importLogBodyList.add("title");
            }
            else if (listingRepository.findDescriptionByListingId(listingId) == null) {
                importLogBodyList.add("description");
            }
            else if (listingRepository.findListingPriceByListingId(listingId) == null) {
                importLogBodyList.add("listing_price");
            }
            else if (listingRepository.findCurrencyByListingId(listingId) == null) {
                importLogBodyList.add("currency");
            }
            else if (listingRepository.findQuantityByListingId(listingId) < 0) {
                importLogBodyList.add("quantity");
            }
            else if (listingRepository.findUploadTimeByListingId(listingId) == null) {
                importLogBodyList.add("upload_time");
            }
            else if (listingRepository.findOwnerEmailAddressByListingId(listingId) == null) {
                importLogBodyList.add("owner_email_address");
            }
            else if (listingRepository.findLocationIdByListingId(listingId) == 0) {
                importLogBodyList.add("location_id");
            }
            else if (listingRepository.findListingStatusByListingId(listingId) == 0) {
                importLogBodyList.add("listing_status");
            }
        } else {
            importLogBodyList.add("");
            importLogBodyList.add("");
            importLogBodyList.add("");
        }
    }

}
