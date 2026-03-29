package controller;

import fileio.DeveloperFileHelper;
import fileio.IFileReadWrite;
import model.Developer;
import utils.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeveloperController {

    private final List<Developer> developers = new ArrayList<>();
    private final IFileReadWrite<Developer> fileHelper = new DeveloperFileHelper();

    public void load() {
        developers.clear();
        try {
            developers.addAll(fileHelper.read());
        } catch (Exception e) {
            System.out.println("Load failed.");
        }
    }

    public void save() throws Exception {
        fileHelper.write(developers);
    }

    public List<Developer> getAll() {
        return new ArrayList<>(developers);
    }

    public boolean existsId(String devId) {
        return findById(devId) != null;
    }

    public Developer findById(String devId) {
        String id = Validator.normalizeId(devId);
        for (Developer d : developers) {
            if (d.getDevId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    public boolean add(Developer d) {
        if (d == null) {
            return false;
        }
        developers.add(d);
        return true;
    }

    public boolean updateSalary(String devId, int newSalary) {
        Developer d = findById(devId);
        if (d == null) {
            return false;
        }
        if (!Validator.validSalary(newSalary)) {
            return false;
        }
        d.setSalaryUsd(newSalary);
        return true;
    }

    public boolean removeIfNoProject(String devId, ProjectController projectCtrl) {
        Developer d = findById(devId);
        if (d == null) {
            return false;
        }

        if (projectCtrl != null && projectCtrl.hasProjectsOfDev(d.getDevId())) {
            return false;
        }
        return developers.remove(d);
    }

    public List<Developer> filterByLanguage(String lang) {
        List<Developer> res = new ArrayList<>();
        for (Developer d : developers) {
            if (d.hasLanguage(lang)) {
                res.add(d);
            }
        }
        return res;
    }

    public void sortBySalaryAsc() {
        developers.sort(Comparator.comparingInt(Developer::getSalaryUsd));
    }
}
