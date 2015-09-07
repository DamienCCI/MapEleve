package fr.damienseyve.mapeleve;

/**
 * Created by stagiaire on 04/09/2015.
 */
public class Planning {

    private String startString;
    private String heureDebString;
    private String heureFinString;
    private String summary;
    private String lieux;

    public Planning(String startString, String heureDebString, String heureFinString, String summary, String lieux) {
        this.startString = startString;
        this.heureDebString = heureDebString;
        this.heureFinString = heureFinString;
        this.summary = summary;
        this.lieux = lieux;
    }

    public String getStartString() {
        return startString;
    }

    public String getHeureDebString() {
        return heureDebString;
    }

    public String getHeureFinString() {
        return heureFinString;
    }

    public String getSummary() {
        return summary;
    }

    public String getLieux() {
        return lieux;
    }
}
