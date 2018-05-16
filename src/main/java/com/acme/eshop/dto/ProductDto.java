package com.acme.eshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * Created by Eleni on 15/5/2018.
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private static final String PRICE_PATTERN = "\\\\d{1,3}[,\\\\.]?(\\\\d{1,2})?";
    private static final String NUMBER_PATTERN = "^\\d*$";
    private static final String CATEGORY_PATTERN = "^[A-z]+$";
    private static final String PATH_PTTERN = "^[:.A-z0-9\\/]+$";

    private String productCode;

    @Pattern(regexp = PRICE_PATTERN, message = "{product.price.invalid}")
    private String price;

    @Pattern(regexp = PATH_PTTERN, message = "{product.imgUrl.invalid}")
    private String imgUrl;

    private String description;

    @Pattern(regexp = NUMBER_PATTERN, message = "{product.stock.invalid}")
    private String stock;

    @Pattern(regexp = NUMBER_PATTERN, message = "{product.purchased.invalid}")
    private String purchased;

    @Pattern(regexp = CATEGORY_PATTERN, message = "{product.purchased.invalid}")
    private String categoryName;

}
