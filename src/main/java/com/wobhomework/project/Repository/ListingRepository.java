package com.wobhomework.project.Repository;

import com.wobhomework.project.Model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @Query(value = "SELECT title FROM listing WHERE id = ?1", nativeQuery = true)
    String findTitleByListingId(UUID listingId);

    @Query(value = "SELECT description FROM listing WHERE id = ?1", nativeQuery = true)
    String findDescriptionByListingId(UUID listingId);

    @Query(value = "SELECT listing_price FROM listing WHERE id = ?1 AND listing_price > 0", nativeQuery = true)
    BigDecimal findListingPriceByListingId(UUID listingId);

    @Query(value = "SELECT currency FROM listing WHERE id = ?1 AND LENGTH(currency) = 3", nativeQuery = true)
    String findCurrencyByListingId(UUID listingId);

    @Query(value = "SELECT quantity FROM listing WHERE id = ?1 AND quantity > 0", nativeQuery = true)
    int findQuantityByListingId(UUID listingId);

    @Query(value = "SELECT upload_time FROM listing WHERE id = ?1", nativeQuery = true)
    Date findUploadTimeByListingId(UUID listingId);

    @Query(value = "SELECT owner_email_address FROM listing WHERE id = ?1 AND owner_email_address LIKE '%_@__%.__%'", nativeQuery = true)
    String findOwnerEmailAddressByListingId(UUID listingId);

    @Query(value = "SELECT location_id FROM listing WHERE id = ?1", nativeQuery = true)
    UUID findLocationIdByListingId(UUID listingId);

    @Query(value = "SELECT marketplace FROM listing WHERE id = ?1", nativeQuery = true)
    int findMarketplaceByListingId(UUID listingId);

    @Query(value = "SELECT listing_status FROM listing WHERE id = ?1", nativeQuery = true)
    int findListingStatusByListingId(UUID listingId);

    @Query(value = "Select listing.* From listing " +
            "JOIN marketplace on marketplace.id = listing.marketplace " +
            "WHERE marketplace.marketplace_name = 'EBAY' " +
            "GROUP BY listing.id, extract(year from listing.upload_time), extract(month from listing.upload_time) " +
            "ORDER BY extract(year from listing.upload_time), extract(month from listing.upload_time)" ,nativeQuery = true)
    List<Listing> findAllEbayMarketplace();

    @Query(value = "Select listing.* From listing " +
            "JOIN marketplace on marketplace.id = listing.marketplace " +
            "WHERE marketplace.marketplace_name = 'AMAZON' " +
            "GROUP BY listing.id, extract(year from listing.upload_time), extract(month from listing.upload_time) " +
            "ORDER BY extract(year from listing.upload_time), extract(month from listing.upload_time)", nativeQuery = true)
    List<Listing> findAllAmazonMarketplace();

    @Query(value = "Select listing.* From listing " +
            "JOIN marketplace on marketplace.id = listing.marketplace " +
            "WHERE marketplace.marketplace_name = 'AMAZON' AND " +
            "(extract(year from listing.upload_time) = :year AND extract(month from listing.upload_time) = :month) " +
            "GROUP BY listing.id, extract(year from listing.upload_time), extract(month from listing.upload_time) " +
            "ORDER BY extract(year from listing.upload_time), extract(month from listing.upload_time)", nativeQuery = true)
    List<Listing> findAllEbayMarketplaceByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query(value = "Select AVG(listing.listing_price) From listing " +
            "JOIN marketplace on marketplace.id = listing.marketplace " +
            "WHERE marketplace.marketplace_name = 'EBAY' AND " +
            "(extract(year from listing.upload_time) = :year AND extract(month from listing.upload_time) = :month) " +
            "GROUP BY extract(year from listing.upload_time), extract(month from listing.upload_time) " +
            "ORDER BY extract(year from listing.upload_time), extract(month from listing.upload_time)", nativeQuery = true)
    BigDecimal getAverageEbayMarketplaceListingPriceByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query(value = "Select listing.* From listing " +
            "JOIN marketplace on marketplace.id = listing.marketplace " +
            "WHERE marketplace.marketplace_name = 'AMAZON' AND " +
            "(extract(year from listing.upload_time) = :year AND extract(month from listing.upload_time) = :month) " +
            "GROUP BY listing.id, extract(year from listing.upload_time), extract(month from listing.upload_time) " +
            "ORDER BY extract(year from listing.upload_time), extract(month from listing.upload_time)", nativeQuery = true)
    List<Listing> findAllAmazonMarketplaceByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
