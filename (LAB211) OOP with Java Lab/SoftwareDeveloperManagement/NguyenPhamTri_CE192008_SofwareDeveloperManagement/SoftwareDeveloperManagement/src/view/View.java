package view;

import model.Developer;
import model.Project;
import utils.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public int showMainMenu() {
        System.out.println("                                              ");
        System.out.println("\n===== Software Developer Management =====");
        String[] options = {
            "List all Developers",
            "Add a new Developer",
            "Search for a Developer by ID",
            "Update a Developer's salary by ID",
            "List all Developers by Language",
            "Add a new Project",
            "List all Projects by Developer (Grouped)",
            "Calculate Total Experience by Dev ID",
            "Remove a Developer by ID",
            "Sort Developers by Salary",
            "Save data to files",
            "Quit program",};
        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }

    public void showDevelopersTable(List<Developer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("(No developers)");
            return;
        }
        System.out.printf("%-8s | %-25s | %-25s | %-8s%n", "ID", "Name", "Languages", "Salary");
        System.out.println("--------------------------------------------------------------------------");
        for (Developer d : list) {
            System.out.printf("%-8s | %-25s | %-25s | %-8d%n",
                    d.getDevId(),
                    crop(d.getFullName(), 25),
                    crop(d.languagesAsText(), 25),
                    d.getSalaryUsd());
        }
    }

    public void showDeveloperDetail(Developer d) {
        if (d == null) {
            return;
        }
        System.out.printf("ID: %s%nName: %s%nLanguages: %s%nSalary(USD): %d%n",
                d.getDevId(),
                d.getFullName(),
                d.languagesAsText(),
                d.getSalaryUsd());
    }

    public void showProjectsOfDeveloper(Developer d, List<Project> projects) {
        System.out.println("\n--------------------------------");
        System.out.printf("%s - %s%n", d.getDevId(), d.getFullName());
        System.out.println("--------------------------------");
        if (projects == null || projects.isEmpty()) {
            System.out.println("(No projects)");
            return;
        }
        System.out.printf("%-8s | %-30s | %-8s | %-12s%n", "ProjID", "Project Name", "Months", "Start Date");
        System.out.println("---------------------------------------------------------------------");
        for (Project p : projects) {
            System.out.printf("%-8s | %-30s | %-8d | %-12s%n",
                    p.getProjectId(),
                    crop(p.getProjectName(), 30),
                    p.getDurationMonths(),
                    p.startDateText());
        }
    }

    public String readOptional(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim(); // may be empty
    }

    public String inputNonEmpty(String label) {
        while (true) {
            System.out.print(label);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Cannot be empty.");
        }
    }

    public String inputNonEmptyOnce(String label) {
        System.out.print(label);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) {
            System.out.println("Cannot be empty.");
            return null;
        }
        return s;
    }

    public String inputDevIdAnyOnce() {
        String id = inputNonEmptyOnce("Developer ID (DEVxxx): ");
        if (id == null) {
            return null;
        }

        if (!Validator.validDevId(id)) {
            System.out.println("Invalid ID. Format DEVxxx.");
            return null;
        }
        return id;
    }

    public int inputSalary() {
        System.out.print("Salary (>= 1000 USD): ");
        while (true) {
            String line = sc.nextLine().trim();
            int x = parseIntSafe(line, -1);
            if (Validator.validSalary(x)) {
                return x;
            }
            System.out.print("Invalid salary. Try Again! \nSalary (>= 1000 USD):");
        }
    }

    public Integer inputSalaryOnce() {
        String s = inputNonEmptyOnce("Salary (>= 1000 USD): ");
        if (s == null) {
            return null;
        }

        try {
            int x = Integer.parseInt(s);
            if (!Validator.validSalary(x)) {
                System.out.println("Invalid salary. Update aborted.");
                return null;
            }
            return x;
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary.");
            return null;
        }
    }

    public String inputDevIdNew(List<Developer> existing) {

        while (true) {
            String id = inputNonEmpty("Developer ID (DEVxxx): ");
            if (!Validator.validDevId(id)) {
                System.out.println("Invalid ID. Format DEVxxx. Try Again!");
                continue;
            }
            if (containsDevId(existing, id)) {
                System.out.println("Duplicated ID. Try Again!");
                continue;
            }
            return id;
        }
    }

    public String inputFullName() {
        System.out.print("Full name: ");
        while (true) {
            String name = sc.nextLine().trim();
            if (Validator.validFullName(name)) {
                return name;
            }
            System.out.print("Invalid name (at least 2 words). Try Again! \nFull name: ");
        }
    }

    public List<String> inputLanguages() {
        System.out.print("Programming languages (comma-separated, e.g., Java, C++): ");
        while (true) {
            String raw = sc.nextLine().trim();
            List<String> langs = Validator.parseLanguages(raw);
            if (Validator.validLanguages(langs)) {
                return langs;
            }
            System.out.print("Languages cannot be empty. Try Again! \nProgramming languages (comma-separated, e.g., Java, C++): ");
        }
    }

    public String inputProjectIdNew(List<Project> existing) {
        while (true) {
            String id = inputNonEmpty("Project ID: ");
            if (containsProjectId(existing, id)) {
                System.out.println("Duplicated Project ID. Enter another!");
                continue;
            }
            return id;
        }
    }

    public String inputProjectName() {
        return inputNonEmpty("Project name: ");
    }

    public int inputDurationMonths() {
        System.out.print("Duration months (>=1): ");
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int x = Integer.parseInt(line);
                if (Validator.validDuration(x)) {
                    return x;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Invalid duration. Enter again: ");
        }
    }

    public LocalDate inputFutureDate() {
        System.out.print("Start date (dd/MM/yyyy, must be future): ");
        while (true) {
            String s = sc.nextLine().trim();
            LocalDate d = Validator.parseDate(s);
            if (Validator.isFutureDate(d)) {
                return d;
            }
            System.out.print("Invalid date or not in future. Enter again: ");
        }
    }

    public static int parseIntSafe(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public String chooseDevIdFromList(List<Developer> devs) {
        if (devs == null || devs.isEmpty()) {
            return null;
        }
        System.out.println("Select a Developer:");
        for (int i = 0; i < devs.size(); i++) {
            Developer d = devs.get(i);
            System.out.printf("%2d) %s - %s%n", i + 1, d.getDevId(), d.getFullName());
        }
        System.out.print("Choose [1.." + devs.size() + "]: ");
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int c = Integer.parseInt(line);
                if (c >= 1 && c <= devs.size()) {
                    return devs.get(c - 1).getDevId();
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Invalid choice. Choose again: ");
        }
    }

    private static boolean containsDevId(List<Developer> list, String id) {
        if (list == null) {
            return false;
        }
        for (Developer d : list) {
            if (d.getDevId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsProjectId(List<Project> list, String id) {
        if (list == null) {
            return false;
        }
        for (Project p : list) {
            if (p.getProjectId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private static String crop(String s, int max) {
        if (s == null) {
            return "";
        }
        if (s.length() <= max) {
            return s;
        }
        return s.substring(0, Math.max(0, max - 3)) + "...";
    }
}
