package com.acme.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Eleni on 17/5/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {

    private static final String NUMBER_PATTERN = "^\\d*$";

    @NotNull(message = "{item.productcode.null}")
    private String productCode;

    @Pattern(regexp = NUMBER_PATTERN, message = "{item.amount.invalid}")
    private String amount;

}
