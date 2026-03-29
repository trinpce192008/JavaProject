package controller;

import fileio.IFileReadWrite;
import fileio.ProjectFileHelper;
import model.Project;
import utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    private final List<Project> projects = new ArrayList<>();
    private final IFileReadWrite<Project> fileHelper = new ProjectFileHelper();

    public void load() {
        projects.clear();
        try {
            projects.addAll(fileHelper.read());
        } catch (Exception e) {
            System.out.println("Load failed.");
        }
    }

    public void save() throws Exception {
        fileHelper.write(projects);
    }

    public List<Project> getAll() {
        return new ArrayList<>(projects);
    }

    public boolean add(Project p) {
        if (p == null) {
            return false;
        }
        projects.add(p);
        return true;
    }

    public List<Project> getByDevId(String devId) {
        String id = Validator.normalizeId(devId);
        List<Project> res = new ArrayList<>();
        for (Project p : projects) {
            if (p.getDevId().equalsIgnoreCase(id)) {
                res.add(p);
            }
        }
        return res;
    }

    public boolean hasProjectsOfDev(String devId) {
        String id = Validator.normalizeId(devId);
        for (Project p : projects) {
            if (p.getDevId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public int totalExperienceMonths(String devId) {
        int sum = 0;
        for (Project p : getByDevId(devId)) {
            sum += p.getDurationMonths();
        }
        return sum;
    }
}
