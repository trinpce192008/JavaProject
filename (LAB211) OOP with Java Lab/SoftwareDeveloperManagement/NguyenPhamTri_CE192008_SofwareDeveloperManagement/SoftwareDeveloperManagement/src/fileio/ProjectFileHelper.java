package fileio;

import model.Project;
import utils.FileUtils;
import utils.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProjectFileHelper implements IFileReadWrite<Project> {

    private final String FILE_NAME = "src\\fileio\\projects.txt";

    @Override
    public List<Project> read() throws Exception {
        List<String> lines = FileUtils.readAllLines(FILE_NAME);
        List<Project> list = new ArrayList<>();
        Set<String> seen = new HashSet<>(); 

        for (String line : lines) {
            if (line == null) {
                continue;
            }

            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s*,\\s*", 5);
            if (parts.length < 5) {
                continue;
            }

            String projId = parts[0].replace("\uFEFF", "").trim();     
            String devId = parts[1].trim();
            String name = parts[2].trim();
            String durStr = parts[3].trim();
            String dateStr = parts[4].trim();
            
            if (projId.isEmpty()) {
                continue;
            }
            if (!seen.add(projId.toUpperCase())) {
                continue;   
            }
            if (!Validator.validDevId(devId)) {
                continue;
            }
            if (!Validator.validProjectName(name)) {
                continue;
            }

            int duration;
            try {
                duration = Integer.parseInt(durStr);
            } catch (NumberFormatException e) {
                continue;
            }
            if (!Validator.validDuration(duration)) {
                continue;
            }

            LocalDate startDate = Validator.parseDate(dateStr);
            if (!Validator.isFutureDate(startDate)) {
                continue;
            }

            list.add(new Project(projId, devId, name, duration, startDate));
        }

        return list;
    }

    @Override
    public boolean write(List<Project> list) throws Exception {
        List<String> lines = new ArrayList<>();
        if (list != null) {
            for (Project p : list) {
                String line = String.format("%s, %s, %s, %d, %s",
                        p.getProjectId(),
                        p.getDevId(),
                        p.getProjectName(),
                        p.getDurationMonths(),
                        p.startDateText()
                );
                lines.add(line);
            }
        }
        FileUtils.writeAllLines(FILE_NAME, lines);
        return true;
    }
}
