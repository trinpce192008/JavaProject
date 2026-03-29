
import controller.DeveloperController;
import controller.ProjectController;
import model.Developer;
import model.Project;
import view.View;

import java.util.List;
import java.util.Scanner;

public class Main {

    private final View view = new View(new Scanner(System.in));
    private final DeveloperController devCtrl = new DeveloperController();
    private final ProjectController projCtrl = new ProjectController();
    private boolean changed = false;

    public Main() {
        devCtrl.load();
        projCtrl.load();
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            try {
                int choice = main.view.showMainMenu();
                switch (choice) {
                    case 1: // List all Developers
                        main.listAllDevelopers();
                        break;

                    case 2: // Add new Developer
                        main.addDeveloper();
                        break;

                    case 3: // Search Developer by ID
                        main.searchDeveloper();
                        break;

                    case 4: // Update salary by ID
                        main.updateSalary();
                        break;

                    case 5: // List Developers by Language
                        main.listByLanguage();
                        break;

                    case 6: // Add new Project
                        main.addProject();
                        break;

                    case 7: // List all Projects by Developer (Grouped)
                        main.listProjectsGrouped();
                        break;

                    case 8: // Calculate Total Experience by Dev ID
                        main.calcTotalExperience();
                        break;

                    case 9: // Remove Developer by ID
                        main.removeDeveloper();
                        break;

                    case 10: // Sort Developers by Salary
                        main.sortDevBySalary();
                        break;

                    case 11: // Save data
                        main.saveData();
                        break;

                    case 12: // Quit
                        main.exit();
                        break;

                    default:
                        main.view.showMessage("This function is not available.");
                }
            } catch (Exception e) {
                main.view.showMessage("Invalid input. Please try again.");
            }
        }
    }

    private void listAllDevelopers() {
        view.showMessage("\n--- All Developers ---\n");
        view.showDevelopersTable(devCtrl.getAll());
    }

    private boolean askContinue(String successMsg, String prompt) {
        view.showMessage("");
        view.showMessage(successMsg);
        String cont = view.readOptional(prompt);
        return cont.equalsIgnoreCase("Y");
    }

    private void addDeveloper() {
        while (true) {
            view.showMessage("\n---- Add New Developer ----");

            List<Developer> all = devCtrl.getAll();
            String id = view.inputDevIdNew(all);
            String name = view.inputFullName();
            List<String> langs = view.inputLanguages();
            int salary = view.inputSalary();

            Developer d = new Developer(id, name, langs, salary);
            boolean ok = devCtrl.add(d);

            if (!ok) {
                view.showMessage("=== Add failed. ===");
                return;
            }

            changed = true;

            if (!askContinue("=== ADD SUCCESS. ===", "Continue adding new? (Y/N): ")) {
                break;
            }
        }
    }

    private void searchDeveloper() {
        view.showMessage("\n---- Search Developer ----");

        String id = view.inputDevIdAnyOnce();
        if (id == null) {
            return;
        }
        Developer d = devCtrl.findById(id);
        if (d == null) {
            view.showMessage("Developer ID does not exist!");
            return;
        }

        view.showDeveloperDetail(d);
    }

    private void updateSalary() {
        while (true) {
            view.showMessage("\n---- Update Developer Salary ----");

            String id = view.inputDevIdAnyOnce();
            if (id == null) {
                view.showMessage("=== Update aborted. ===");
                return;
            }

            Developer d = devCtrl.findById(id);
            if (d == null) {
                view.showMessage("Developer ID does not exist!");
                return;
            }

            Integer newSalary = view.inputSalaryOnce();
            if (newSalary == null) {
                return; // sai -> menu ngay
            }
            boolean ok = devCtrl.updateSalary(id, newSalary);
            if (!ok) {
                view.showMessage("Update failed.");
                return;
            }

            changed = true;

            if (!askContinue("=== UPDATE SUCCESS. ===", "Continue updating new? (Y/N): ")) {
                break;
            }
        }
    }

    private void listByLanguage() {
        view.showMessage("\n---- List Developers By Language ----");

        String lang = view.inputNonEmpty("Language to search (e.g., Java): ");
        List<Developer> list = devCtrl.filterByLanguage(lang);

        if (list.isEmpty()) {
            view.showMessage("No developer matches!");
            return;
        }

        view.showDevelopersTable(list);
    }

    private void addProject() {
        if (devCtrl.getAll().isEmpty()) {
            view.showMessage("No developers available. Please add a developer first.");
            return;
        }

        while (true) {
            view.showMessage("\n---- Add New Project ----");

            String projId = view.inputProjectIdNew(projCtrl.getAll());

            String devId = view.chooseDevIdFromList(devCtrl.getAll());
            if (devId == null) {
                view.showMessage("No developer selected!");
                return;
            }

            String projName = view.inputProjectName();
            int duration = view.inputDurationMonths();
            java.time.LocalDate startDate = view.inputFutureDate();

            Project p = new Project(projId, devId, projName, duration, startDate);

            boolean ok = projCtrl.add(p);
            if (!ok) {
                view.showMessage("Add project failed.");
                return;
            }

            changed = true;

            if (!askContinue("=== ADD PROJECT SUCCESS. ===", "Continue adding new project? (Y/N): ")) {
                break;
            }
        }
    }

    private void listProjectsGrouped() {
        List<Developer> devs = devCtrl.getAll();
        if (devs.isEmpty()) {
            view.showMessage("(No developers)");
            return;
        }
        for (Developer d : devs) {
            List<Project> ps = projCtrl.getByDevId(d.getDevId());
            view.showProjectsOfDeveloper(d, ps);
        }
    }

    private void calcTotalExperience() {
        view.showMessage("\n---- Calculate Total Experience ----");

        String id = view.inputDevIdAnyOnce();
        if (id == null) {
            return;
        }

        Developer d = devCtrl.findById(id);
        if (d == null) {
            view.showMessage("Developer ID does not exist!");
            return;
        }

        int total = projCtrl.totalExperienceMonths(id);
        view.showMessage("Total experience (months) of " + d.getDevId() + " = " + total);
    }

    private void removeDeveloper() {
        while (true) {
            view.showMessage("\n---- Remove Developer ----");

            String id = view.inputDevIdAnyOnce();
            if (id == null) {
                return;
            }

            Developer d = devCtrl.findById(id);
            if (d == null) {
                view.showMessage("Developer ID does not exist!");
                return;
            }

            if (projCtrl.hasProjectsOfDev(id)) {
                view.showMessage("Cannot delete: Developer is assigned to projects.");
                return;
            }

            boolean ok = devCtrl.removeIfNoProject(id, projCtrl);
            if (!ok) {
                view.showMessage("Remove failed.");
                return;
            }

            changed = true;

            if (!askContinue("=== REMOVE SUCCESS. ===", "Continue removing new? (Y/N): ")) {
                break;
            }
        }
    }

    private void sortDevBySalary() {
        view.showMessage("\n---- Sort Developers By Salary (ASC) ----");
        devCtrl.sortBySalaryAsc();
        view.showDevelopersTable(devCtrl.getAll());
    }

    private boolean saveAll() {
        try {
            devCtrl.save();
            projCtrl.save();
            view.showMessage("Saved to files successfully.");
            return true;
        } catch (Exception e) {
            view.showMessage("Save failed: " + e.getMessage());
            return false;
        }
    }

    private void saveData() {
        if (!changed) {
            view.showMessage("No changes to save.");
            return;
        }

        boolean ok = saveAll();
        if (ok) {
            changed = false;
        } else {
            view.showMessage("Please try again.");
        }
    }

    private void exit() {
        if (!changed) {
            view.showMessage("=== No changes to save. Bye! ===");
        } else {
            String cont = view.readOptional("Data has been modified. Save changes? (Y/N): ");

            if (cont.equalsIgnoreCase("Y")) {

                if (saveAll()) {
                    System.exit(0);
                }
            }

        }
        view.showMessage("Exiting program...");
        System.exit(0);
    }
}
