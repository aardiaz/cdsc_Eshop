package com.cdsc.eshopdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdsc.eshopdemo.dto.ProductDto;
import com.cdsc.eshopdemo.entity.Category;
import com.cdsc.eshopdemo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //search
    Page<ProductDto> findByTitleContaining(String subTitle, Pageable pageable);

    Page<ProductDto> findByLiveTrue(Pageable pageable);

    Page<ProductDto> findByCategory(Category category,Pageable pageable);

}
