package pwa.mensaapp.objects;
import pwa.mensaapp.response.ResponseMeals;

import java.util.*;
public class MensaOverview {
    private String name;
    private String adresse;
    private String offenJaNein;
    List<ResponseMeals> gerichte;

    public MensaOverview() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getOffenJaNein() {
        return offenJaNein;
    }

    public void setOffenJaNein(String offenJaNein) {
        this.offenJaNein = offenJaNein;
    }

    public List<ResponseMeals> getGerichte() {
        return gerichte;
    }

    public void setGerichte(List<ResponseMeals> gerichte) {
        this.gerichte = gerichte;
    }
}
