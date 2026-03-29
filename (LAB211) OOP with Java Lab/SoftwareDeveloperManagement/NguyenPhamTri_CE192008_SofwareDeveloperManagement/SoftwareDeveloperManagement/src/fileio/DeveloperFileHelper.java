package fileio;

import model.Developer;
import utils.FileUtils;
import utils.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DeveloperFileHelper implements IFileReadWrite<Developer> {
    private final String FILE_NAME = "src\\fileio\\developers.txt";

    @Override
        public List<Developer> read() throws Exception {
        List<String> lines = FileUtils.readAllLines(FILE_NAME);
        List<Developer> list = new ArrayList<>();
        Set<String> seen = new HashSet<>(); 

        for (String line : lines) {
            String[] capSplit = line.split(",(?=\\s*\\d+\\s*$)",2);
            if (capSplit.length < 2) {
                continue;
            }
            String[] parts = capSplit[0].split("\\s*,\\s*", 3);
            if (parts.length < 3) {
                continue;
            }
            String id = parts[0].replace("\uFEFF", "").trim();
            String name = parts[1].trim();
            String langsPart = parts[2].trim();
            String salaryPart =capSplit[1].trim();

            List<String> langs = Validator.parseLanguages(langsPart);

            int salary;
            try {
                salary = Integer.parseInt(salaryPart);
            } catch (NumberFormatException e) {
                continue;
            }

            if (!Validator.validDevId(id)) continue;
            if (!Validator.validFullName(name)) continue;
            if (!Validator.validLanguages(langs)) continue;
            if (!Validator.validSalary(salary)) continue;
            if (!seen.add(id.toUpperCase())) continue;

            list.add(new Developer(id, name, langs, salary));
        }

        return list;
    }

    @Override
    public boolean write(List<Developer> list) throws Exception {
        List<String> lines = new ArrayList<>();
        if (list != null) {
            for (Developer d : list) {
                String line = String.format("%s, %s, %s, %d",
                        d.getDevId(),
                        d.getFullName(),
                        d.languagesAsFileText(),
                        d.getSalaryUsd()
                );
                lines.add(line);
            }
        }
        FileUtils.writeAllLines(FILE_NAME, lines);
        return true;
    }
}
