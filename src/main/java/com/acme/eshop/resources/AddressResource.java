package com.acme.eshop.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Eleni on 16/5/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResource {

    private static final String POSTAL_CODE_PATTERN = "\\d\\d\\d\\d\\d";
    private static final String TOWN_PATTERN = "^[A-z]+$";
    private static final String STREET_PATTERN = "^[a-zA-Z0-9 ,]*$";
    private static final int POSTAL_CODE_SIZE = 5;


    @Pattern(regexp = POSTAL_CODE_PATTERN, message = "{address.code.invalid}")
    @Size(min=POSTAL_CODE_SIZE, max=POSTAL_CODE_SIZE, message = "{address.code.size}")
    private String postalCode;

    @Pattern(regexp = TOWN_PATTERN, message = "{address.town.invalid}")
    private String town;

    @Pattern(regexp = POSTAL_CODE_PATTERN, message = "{address.street.invalid}")
    private String street;
}
