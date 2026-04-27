package com.cdsc.eshopdemo.dto;

import java.time.LocalDate;

import com.cdsc.eshopdemo.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int quantity;
    private LocalDate addedDate;
    private boolean live;
    private boolean stock;
    private String productImageName;
    private  CategoryDto category;
}
