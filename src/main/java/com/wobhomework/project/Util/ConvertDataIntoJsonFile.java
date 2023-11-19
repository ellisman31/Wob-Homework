package com.wobhomework.project.Util;

import com.github.cliftonlabs.json_simple.Jsoner;
import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Model.Marketplace;
import com.wobhomework.project.Repository.ListingRepository;
import com.wobhomework.project.Repository.MarketplaceRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ConvertDataIntoJsonFile {

    private static final String JSON_FILE_NAME = "reportTest";
    private static final String JSON_EXTENSION = ".json";
    private final ListingRepository listingRepository;
    private final MarketplaceRepository marketplaceRepository;
    private boolean checkCurrentYear = false;
    private boolean checkCurrentMonth = false;

    public void saveFileToDirectory() {
        String jsonObject = createJsonObjectWithData().toString();
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(JSON_FILE_NAME + JSON_EXTENSION));
            writer.write(jsonObject);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JSONObject createJsonObjectWithData() {
        JSONObject jsonObject = new JSONObject();
        mainReportJsonData(jsonObject);
        monthlyReportJsonData(jsonObject);
        return jsonObject;
    }

    private void mainReportJsonData(JSONObject jsonObject) {
        jsonObject.put("totalListingCount", listingRepository.findAll().size());
        jsonObject.put("totalEbayListingCount", listingRepository.findAllEbayMarketplace().size());
        jsonObject.put("totalEbayListingPrice", sumTotalListingPrice(listingRepository.findAllEbayMarketplace()));
        jsonObject.put("averageEbayListingPrice", 443.65);
        jsonObject.put("totalAmazonListingCount", listingRepository.findAllAmazonMarketplace().size());
        jsonObject.put("totalAmazonListingPrice", sumTotalListingPrice(listingRepository.findAllAmazonMarketplace()));
        jsonObject.put("bestListerEmailAddress", "");
    }

    private void monthlyReportJsonData(JSONObject jsonObject) {
        List <List<Listing>> findAllEbayMarketplaceByYearAndMonth = new ArrayList<>();
        List <List<Listing>> findAllAmazonMarketplaceByYearAndMonth = new ArrayList<>();
        Calendar myCal = Calendar.getInstance();
        listingRepository.findAllEbayMarketplace().forEach(ebayMarketplace -> {
            if (ebayMarketplace.getUpload_time() != null) {
                myCal.setTime(ebayMarketplace.getUpload_time());
                findAllEbayMarketplaceByYearAndMonth.add(listingRepository.findAllEbayMarketplaceByYearAndMonth(
                        myCal.get(Calendar.YEAR), myCal.get(Calendar.MONTH)));
            }

        });
        listingRepository.findAllAmazonMarketplace().forEach(amazonMarketplace -> {
            if (amazonMarketplace.getUpload_time() != null) {
                myCal.setTime(amazonMarketplace.getUpload_time());
                findAllAmazonMarketplaceByYearAndMonth.add(listingRepository.findAllAmazonMarketplaceByYearAndMonth(
                        myCal.get(Calendar.YEAR), myCal.get(Calendar.MONTH)));
            }
        });
        collectInformationForJSONArray(findAllEbayMarketplaceByYearAndMonth, jsonObject);
        collectInformationForJSONArray(findAllAmazonMarketplaceByYearAndMonth, jsonObject);
    }

    private void collectInformationForJSONArray(List<List<Listing>> marketPlaceByYearAndMonth, JSONObject jsonObject) {
        JSONArray jsonArrayYear = new JSONArray();
        JSONArray jsonArrayMonth = new JSONArray();
        Calendar firstCalendar = Calendar.getInstance();
        Calendar secondCalendar = Calendar.getInstance();

        for (int i = 0; i <= marketPlaceByYearAndMonth.size()-1; i++) {
            JSONObject newJsonObject = new JSONObject();
            for (int j = 0; j <= marketPlaceByYearAndMonth.get(i).size()-2; j++) {
                Listing currentListing = marketPlaceByYearAndMonth.get(i).get(j);
                List <Listing> currentList = marketPlaceByYearAndMonth.get(i);
                Date currentListingUploadTime = currentListing.getUpload_time();
                Date nextListingUploadTime =  marketPlaceByYearAndMonth.get(i).get(j+1).getUpload_time();
                firstCalendar.setTime(currentListingUploadTime);
                secondCalendar.setTime(nextListingUploadTime);
                checkDateAndMonth(firstCalendar, secondCalendar, jsonArrayYear, jsonArrayMonth, currentListing, currentList, newJsonObject, jsonObject);
            }
        }
    }

    private void checkDateAndMonth(Calendar firstCalendar, Calendar secondCalendar,
                                   JSONArray jsonArrayYear, JSONArray jsonArrayMonth,
                                   Listing currentListing, List <Listing> currentList,
                                   JSONObject newJsonObject, JSONObject jsonObject) {
        int currentYear;
        int currentMonth;
        if (firstCalendar.get(Calendar.YEAR) > secondCalendar.get(Calendar.YEAR) && !checkCurrentYear) {
            checkCurrentYear = true;
            jsonArrayYear.clear();
            currentYear = firstCalendar.get(Calendar.YEAR);
            jsonArrayYear.put(currentYear);
        } else {
            checkCurrentYear = false;
            jsonArrayYear.clear();
            currentYear = secondCalendar.get(Calendar.YEAR);
            jsonArrayYear.put(currentYear);
        }
        if (firstCalendar.get(Calendar.MONTH) > secondCalendar.get(Calendar.MONTH) && !checkCurrentMonth) {
            checkCurrentMonth = true;
            jsonArrayMonth.clear();
            currentMonth = firstCalendar.get(Calendar.MONTH);
            jsonArrayMonth.put(currentMonth);
            jsonArrayMonth.put(gatherInformationForMonthlyReport(currentListing, currentList, newJsonObject, currentYear, currentMonth));
            jsonArrayYear.put(jsonArrayMonth);
        } else {
            checkCurrentMonth = false;
            jsonArrayMonth.clear();
            currentMonth = secondCalendar.get(Calendar.MONTH);
            jsonArrayMonth.put(currentMonth);
            jsonArrayMonth.put(gatherInformationForMonthlyReport(currentListing, currentList, newJsonObject, currentYear, currentMonth));
        }
        jsonArrayYear.put(jsonArrayMonth);
        jsonObject.put("monthlyReport", jsonArrayYear);
    }

    private JSONObject gatherInformationForMonthlyReport(Listing currentListing, List<Listing> currentList,
                                                         JSONObject newJsonObject,
                                                         int currentYear, int currentMonth) {
        Optional<Marketplace> marketplace = marketplaceRepository.findById(currentListing.getMarketplace());
        if (marketplace.isPresent()) {
            if (Objects.equals(marketplace.get().getMarketplace_name(), "EBAY")) {
                newJsonObject.put("totalEbayListingCountPerMonth", currentList.size());
                newJsonObject.put("totalEbayListingPricePerMonth", sumTotalListingPrice(currentList));
                newJsonObject.put("averageEbayListingPricePerMonth",listingRepository.getAverageEbayMarketplaceListingPriceByYearAndMonth(currentYear, currentMonth));
                newJsonObject.put("averageEbayListingPricePerMonth", 0);
            } else if (Objects.equals(marketplace.get().getMarketplace_name(), "AMAZON")) {
                newJsonObject.put("totalAmazonListingCountPerMonth", currentList.size());
                newJsonObject.put("totalAmazonListingPricePerMonth", sumTotalListingPrice(currentList));
            }
        }
        return newJsonObject;
    }

    private BigDecimal sumTotalListingPrice(List<Listing> listings) {
        BigDecimal sumTotalListingPrice = new BigDecimal(0);
        listings.forEach(listing -> sumTotalListingPrice.add(listing.getListing_price()));
        return sumTotalListingPrice;
    }

}
