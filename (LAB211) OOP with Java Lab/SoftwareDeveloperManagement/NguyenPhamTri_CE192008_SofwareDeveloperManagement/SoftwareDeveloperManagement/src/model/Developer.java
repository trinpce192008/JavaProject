package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Developer {
    private String devId;
    private String fullName;
    private List<String> languages = new ArrayList<>();
    private int salaryUsd; 


    public Developer(String devId, String fullName, List<String> languages, int salaryUsd) {
        this.devId = devId;
        this.fullName = fullName;
        if (languages != null) this.languages = new ArrayList<>(languages);
        this.salaryUsd = salaryUsd;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getLanguages() {
        return new ArrayList<>(languages);
    }

    public void setLanguages(List<String> languages) {
        this.languages = (languages == null) ? new ArrayList<>() : new ArrayList<>(languages);
    }

    public int getSalaryUsd() {
        return salaryUsd;
    }

    public void setSalaryUsd(int salaryUsd) {
        this.salaryUsd = salaryUsd;
    }

    public boolean hasLanguage(String lang) {
        if (lang == null) return false;
        String q = lang.trim().toLowerCase();
        for (String s : languages) {
            if (s != null && s.trim().toLowerCase().equals(q)) return true;
        }
        return false;
    }

    public String languagesAsText() {
        StringJoiner sj = new StringJoiner(", ");
        for (String l : languages) sj.add(l);
        return sj.toString();
    }

    public String languagesAsFileText() {
        return "[" + languagesAsText() + "]";
    }

    @Override
    public String toString() {
        return String.format("Developer{devId='%s', fullName='%s', languages=%s, salaryUsd=%d}",
                devId, fullName, languages, salaryUsd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        Developer developer = (Developer) o;
        return Objects.equals(devId, developer.devId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devId);
    }
}
