package org.example.Atto.util;

public class PhoneValidationUtil {
    public static boolean isValidPhone(String phone) {
        if (phone.length() != 13) {
            System.out.println("Enter valid phone number");
            return false;
        } else if (!phone.startsWith("+998")) {
            System.out.println("Enter valid phone number");
            return false;
        }
        return true;
    }
}
