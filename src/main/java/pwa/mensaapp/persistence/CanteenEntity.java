package pwa.mensaapp.persistence;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.*;

@Entity
public class CanteenEntity {
    @Id
    private Long id;
    private String name;
    private String city;
    private String adresse;
    private Double nearLAT;
    private Double nearLNG;
    // über API Days kann auch leer sein weil nicht von jedem Tag ein Status übermittelt wird
    private Boolean closed;




    public CanteenEntity() {
    }

    public CanteenEntity(Long id, String name, String city, String adresse, Double nearLAT, Double nearLNG) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.adresse = adresse;
        this.nearLAT = nearLAT;
        this.nearLNG = nearLNG;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getNearLAT() {
        return nearLAT;
    }

    public void setNearLAT(Double nearLAT) {
        this.nearLAT = nearLAT;
    }

    public Double getNearLNG() {
        return nearLNG;
    }

    public void setNearLNG(Double nearLNG) {
        this.nearLNG = nearLNG;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
