package com.acme.eshop.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Eleni on 15/5/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResource {

    private static final String PRICE_PATTERN = "\\\\d{1,3}[,\\\\.]?(\\\\d{1,2})?";
    private static final String NUMBER_PATTERN = "^\\d*$";
    private static final String CATEGORY_PATTERN = "^[A-z]+$";
    private static final String PATH_PATTERN = "^[:.A-z0-9\\/]+$";

    @NotNull(message = "{product.code.null}")
    private String productCode;

    @NotNull(message = "{product.price.null}")
    @Pattern(regexp = PRICE_PATTERN, message = "{product.price.invalid}")
    private String price;

    @Pattern(regexp = PATH_PATTERN, message = "{product.imgUrl.invalid}")
    private String imgUrl;

    private String description;

    @NotNull(message = "{product.stock.null}")
    @Pattern(regexp = NUMBER_PATTERN, message = "{product.stock.invalid}")
    private String stock;

    @Pattern(regexp = NUMBER_PATTERN, message = "{product.purchased.invalid}")
    private String purchased;

    @NotNull(message = "{product.category.null}")
    @Pattern(regexp = CATEGORY_PATTERN, message = "{product.category.invalid}")
    private String categoryName;

}
