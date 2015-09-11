package fr.damienseyve.mapeleve;


public class Eleve {

    public int id;
    public String prenomEleve;
    public String nomEleve;
    public String adresseEleve;
    public String cpEleve;
    public String villeEleve;
    public String telEleve;
    public static Eleve eleveSelect;

    public Eleve(int id, String prenomEleve, String nomEleve, String adresseEleve, String cpEleve, String villeEleve, String telEleve) {
        super();
        this.id = id;
        this.prenomEleve = prenomEleve;
        this.nomEleve = nomEleve;
        this.adresseEleve = adresseEleve;
        this.cpEleve = cpEleve;
        this.villeEleve = villeEleve;
        this.telEleve = telEleve;
    }

    @Override
    public String toString() {
        return "[Contact] " + id + prenomEleve + nomEleve + adresseEleve + cpEleve + villeEleve + telEleve;
    }

    public int getId() {
        return id;
    }

    public String getPrenomEleve() {
        return prenomEleve;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public String getAdresseEleve() {
        return adresseEleve;
    }

    public String getCpEleve() {
        return cpEleve;
    }

    public String getVilleEleve() {
        return villeEleve;
    }

    public String getTelEleve() {
        return telEleve;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVilleEleve(String villeEleve) {
        this.villeEleve = villeEleve;
    }

    public void setPrenomEleve(String prenomEleve) {
        this.prenomEleve = prenomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public void setAdresseEleve(String adresseEleve) {
        this.adresseEleve = adresseEleve;
    }

    public void setCpEleve(String cpEleve) {
        this.cpEleve = cpEleve;
    }

    public void setTelEleve(String telEleve) {
        this.telEleve = telEleve;
    }
}
