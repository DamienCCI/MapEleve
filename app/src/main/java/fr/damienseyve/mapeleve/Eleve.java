package fr.damienseyve.mapeleve;

/**
 * Created by stagiaire on 07/09/2015.
 */
public class Eleve {

    public String prenomEleve;
    public String nomEleve;
    public String adresseEleve;
    public String cpEleve;
    public String villeEleve;
    public String telEleve;
    public static Eleve eleveSelect;

    public Eleve(String _prenomEleve, String _nomEleve, String _adresseEleve, String _cpEleve, String _villeEleve, String _telEleve) {
        this.prenomEleve = _prenomEleve;
        this.nomEleve = _nomEleve;
        this.adresseEleve = _adresseEleve;
        this.cpEleve = _cpEleve;
        this.villeEleve = _villeEleve;
        this.telEleve = _telEleve;
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
