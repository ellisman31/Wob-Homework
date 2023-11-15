package com.wobhomework.project.Util;


import com.wobhomework.project.Model.Listing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Repository
public class ServiceUtil {

    private final WebClient.Builder webClientBuilder;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String DATE_PATTERN = "MM/dd/yyyy";


    public Object restCall(String url, Object[] object) {
        return webClientBuilder.build().get()
                .uri(url)
                .retrieve()
                .bodyToMono(object.getClass())
                .block();
    }

    public boolean isEmailAddressIsValid(String emailAddress) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress);
        return matcher.matches();
    }

    public boolean isDateFormatIsValid(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        try {
            String dateToString = formatter.format(date);
            formatter.parse(dateToString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    //TODO: reverse the values of everything for invalid response.
    public boolean collectionInvalidAPIResponseData(Listing listing) {
        boolean idIsNotNull = listing.getId() != null;
        boolean titleIsNotNull = listing.getTitle() != null;
        boolean descriptionIsNotNull = listing.getDescription() != null;
        boolean locationIdIsNotNull = listing.getLocation_id() != null;
        boolean listingPriceIsValid = listing.getListing_price() != null && listing.getListing_price().compareTo(BigDecimal.ZERO) > 0;
        boolean currencyIsValid = listing.getCurrency() != null && listing.getCurrency().length() == 3;
        boolean quantityIsValid = listing.getQuantity() > 0;
        boolean listingStatusIsValid = listing.getListing_status() != 0;
        boolean marketPlaceIsNotNull = listing.getMarketplace() != 0;
        boolean ownerEmailAddressIsValid = listing.getOwner_email_address() != null && isEmailAddressIsValid(listing.getOwner_email_address());
        boolean dateFieldIsValid = listing.getUpload_time() != null && isDateFormatIsValid(listing.getUpload_time());
        return idIsNotNull && titleIsNotNull &&
                descriptionIsNotNull && locationIdIsNotNull &&
                listingPriceIsValid && currencyIsValid &&
                quantityIsValid && listingStatusIsValid &&
                marketPlaceIsNotNull && ownerEmailAddressIsValid && dateFieldIsValid;
    }

}
