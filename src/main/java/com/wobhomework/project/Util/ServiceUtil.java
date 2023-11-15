package com.wobhomework.project.Util;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

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

    public boolean isDateFormatIsValid(LocalDate date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        try {
            String dateToString = date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
            formatter.parse(dateToString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
