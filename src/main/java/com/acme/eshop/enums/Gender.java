package com.acme.eshop.enums;

/**
 * Created by Eleni on 9/5/2018.
 */
public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    public static Gender fromString(String genderString) {
        if (genderString == null)
            return null;

        Gender gender;
        genderString = genderString.toUpperCase();
        switch (genderString) {
            case "MALE":
                return MALE;
            case "FEMALE":
                return FEMALE;
            case "OTHER":
                return OTHER;
            default:
                throw new RuntimeException("UNKNOWN gender" + genderString);
        }
    }
}
