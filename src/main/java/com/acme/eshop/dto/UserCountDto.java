package com.acme.eshop.dto;

import com.acme.eshop.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Eleni on 6/2/2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCountDto {

    private User user;
    private Long orderCount;

}
