package A3.vorlesung;

import java.util.Objects;

public class Vorlesung {

    private String studiengruppe;
    private String titel;
    private String dozent;
    private String teilnehmer;

    public Vorlesung(String studiengruppe, String titel, String dozent, String teilnehmer) {
        this.studiengruppe = studiengruppe;
        this.titel = titel;
        this.dozent = dozent;
        this.teilnehmer = teilnehmer;
    }

    public String getStudiengruppe() {
        return studiengruppe;
    }

    public void setStudiengruppe(String studiengruppe) {
        this.studiengruppe = studiengruppe;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDozent() {
        return dozent;
    }

    public void setDozent(String dozent) {
        this.dozent = dozent;
    }

    public String getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(String teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vorlesung)) return false;
        Vorlesung vorlesung = (Vorlesung) o;
        return Objects.equals(getStudiengruppe(), vorlesung.getStudiengruppe()) &&
                Objects.equals(getTitel(), vorlesung.getTitel()) &&
                Objects.equals(getDozent(), vorlesung.getDozent()) &&
                Objects.equals(getTeilnehmer(), vorlesung.getTeilnehmer());
    }

}
