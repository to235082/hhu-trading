package com.hhu.tradingplatform.repository;

import com.hhu.tradingplatform.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
