package com.wobhomework.project.Repository;

import com.wobhomework.project.Model.Listing;
import com.wobhomework.project.Model.ListingStatus;
import com.wobhomework.project.Model.Location;
import com.wobhomework.project.Model.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @Query(value = "SELECT marketplace.marketplace_name FROM marketplace JOIN listing ON marketplace.id = ?2 WHERE listing.id = ?1", nativeQuery = true)
    String findMarketplaceNameByMarketplaceIdForListingId(UUID listingId, int marketPlaceId);

    /*
    @Query(value = "SELECT new Location(location.id, location.manager_name, location.name, location.primary_address, location.secondary_address" +
            "location.country, location.town, location.postal_code) FROM location JOIN listing ON location.id = ?2 WHERE listing.id = ?1", nativeQuery = true)
    Optional<Location> findLocationByLocationIdForListingId(UUID listingId, int locationId);

    @Query(value = "SELECT status_name FROM listing_status JOIN listing ON listing_status.id = ?2 WHERE listing.id = ?1 AND NUMERIC(listing.listing_status, 2)", nativeQuery = true)
    String findListingStatusNameByListingStatusIdForListingId(UUID listingId, int listingStatusId);
     */

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
