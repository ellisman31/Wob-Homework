package com.wobhomework.project.Repository;

import com.wobhomework.project.Model.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Integer> {
}
