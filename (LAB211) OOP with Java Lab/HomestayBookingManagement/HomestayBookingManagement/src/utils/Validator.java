/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author TRI
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {

    private static final String PHONE_PATTERN = "^0\\d{9}$";
    private static final Set<String> VIETTEL_PREFIXES = new HashSet<>(Arrays.asList("086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"));
    private static final Set<String> VNPT_PREFIXES = new HashSet<>(Arrays.asList("088", "091", "094", "083", "084", "085"));



    public static boolean validHomeID(String s) {
        return s != null && s.trim().matches("^HS\\d{4}$");
    }

    public static boolean validTourID(String s) {
        return s != null && s.trim().matches("^T\\d{5}$");
    }

    public static boolean validBookingID(String s) {
        return s != null && s.trim().matches("^B\\d{5}$");
    }

    public static boolean validPhone(String phone) {
        return phone != null && phone.matches(PHONE_PATTERN);
    }

    public static boolean isViettelOrVNPT(String phone) {
        if (!validPhone(phone)) {
            return false;
        }
        String prefix = phone.substring(0, 3);
        return VIETTEL_PREFIXES.contains(prefix) || VNPT_PREFIXES.contains(prefix);
    }
}
