package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern DEV_ID = Pattern.compile("^DEV\\d{3}$");
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static boolean validDevId(String id) {
        return id != null && DEV_ID.matcher(id.trim()).matches();
    }

    public static boolean validFullName(String name) {
        if (name == null) return false;
        String s = name.trim();
        if (s.isEmpty()) return false;
        String[] parts = s.split("\\s+");
        return parts.length >= 2;
    }

    public static List<String> parseLanguages(String raw) {
        if (raw == null) return new ArrayList<>();
        String s = raw.trim();
        if (s.startsWith("[") && s.endsWith("]")) {
            s = s.substring(1, s.length() - 1).trim();
        }
        List<String> list = new ArrayList<>();
        if (s.isEmpty()) return list;

        String[] parts = s.split(",");
        for (String p : parts) {
            String x = p.trim();
            if (!x.isEmpty()) list.add(x);
        }
        return list;
    }

    public static boolean validLanguages(List<String> langs) {
        return langs != null && !langs.isEmpty();
    }

    public static boolean validSalary(int salary) {
        return salary >= 1000;
    }


    public static boolean validProjectName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean validDuration(int months) {
        return months >= 1;
    }

    public static LocalDate parseDate(String ddMMyyyy) {
        if (ddMMyyyy == null) return null;
        try {
            return LocalDate.parse(ddMMyyyy.trim(), DATE_FMT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isFutureDate(LocalDate d) {
        if (d == null) return false;
        return d.isAfter(LocalDate.now());
    }

    public static String normalizeId(String id) {
        return (id == null) ? "" : id.trim().toUpperCase();
    }
}
