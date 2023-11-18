package com.wobhomework.project.Repository;

import com.wobhomework.project.Model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @Query(value = "SELECT title FROM listing WHERE id = ?1", nativeQuery = true)
    String findTitleByListingId(UUID listingId);

    @Query(value = "SELECT description FROM listing WHERE id = ?1", nativeQuery = true)
    String findDescriptionByListingId(UUID listingId);

    @Query(value = "SELECT listing_price FROM listing WHERE id = ?1 AND listing_price < 0", nativeQuery = true)
    BigDecimal findListingPriceByListingId(UUID listingId);

    @Query(value = "SELECT currency FROM listing WHERE id = ?1 AND LENGTH(currency) != 3", nativeQuery = true)
    String findCurrencyByListingId(UUID listingId);

    @Query(value = "SELECT quantity FROM listing WHERE id = ?1 AND quantity > 0", nativeQuery = true)
    int findQuantityByListingId(UUID listingId);

    @Query(value = "SELECT upload_time FROM listing WHERE id = ?1 AND upload_time != '^{4}/{2}/{2}'", nativeQuery = true)
    Date findUploadTimeByListingId(UUID listingId);

    @Query(value = "SELECT owner_email_address FROM listing WHERE id = ?1 AND owner_email_address != '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'", nativeQuery = true)
    String findOwnerEmailAddressByListingId(UUID listingId);

    @Query(value = "SELECT location_id FROM listing WHERE id = ?1", nativeQuery = true)
    int findLocationIdByListingId(UUID listingId);

    @Query(value = "SELECT marketplace FROM listing WHERE id = ?1", nativeQuery = true)
    int findMarketplaceByListingId(UUID listingId);

    @Query(value = "SELECT listing_status FROM listing WHERE id = ?1", nativeQuery = true)
    int findListingStatusByListingId(UUID listingId);
}
