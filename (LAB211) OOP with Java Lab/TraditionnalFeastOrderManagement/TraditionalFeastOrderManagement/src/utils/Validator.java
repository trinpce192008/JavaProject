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

    public static final double DEFAULT_FEE = 6000000;

    private static final String CUS_ID_PATTERN = "^[CGK]\\d{4}$";
    private static final String NAME_PATTERN = "^.{2,25}$";
    private static final String PHONE_PATTERN = "^0\\d{9}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String POSITIVE_INT_VALID = "^[1-9]\\d*";
    public static final String MENU_ID = "^[Pp][Ww]\\d{3}$";

    private static final Set<String> VIETTEL_PREFIXES = new HashSet<>(Arrays.asList("086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"));
    private static final Set<String> VNPT_PREFIXES = new HashSet<>(Arrays.asList("088", "091", "094", "083", "084", "085"));

    public static boolean validCustomer(String id) {
        return id != null && id.matches(CUS_ID_PATTERN);
    }
    public static boolean validMenuId(String id) {
        return id != null && id.matches(MENU_ID);
    }
    public static boolean validPositiveInt(String id) {
        return id != null && id.matches(POSITIVE_INT_VALID);
    }

    public static boolean validName(String name) {
        return name != null && name.matches(NAME_PATTERN);
    }

    public static boolean validPhone(String phone) {
        return phone != null && phone.matches(PHONE_PATTERN);
    }

    public static boolean validEmail(String email) {
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static boolean isViettelOrVNPT(String phone) {
        if (!validPhone(phone)) {
            return false;
        }
        String prefix = phone.substring(0, 3);
        return VIETTEL_PREFIXES.contains(prefix) || VNPT_PREFIXES.contains(prefix);
    }
}
