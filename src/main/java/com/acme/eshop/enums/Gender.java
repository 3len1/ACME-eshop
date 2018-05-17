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
                gender = MALE;
                break;
            case "FEMALE":
                gender = FEMALE;
                break;
            case "OTHER":
                gender = OTHER;
                break;
            default:
                throw new RuntimeException("UNKNOWN gender" + genderString);
        }
        return gender;
    }
}
