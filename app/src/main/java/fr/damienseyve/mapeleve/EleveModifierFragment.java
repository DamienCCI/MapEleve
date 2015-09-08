package fr.damienseyve.mapeleve;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class EleveModifierFragment extends Fragment {

    public String prenomEleve;
    public String nomEleve;
    public String adresseEleve;
    public String cpEleve;
    public String villeEleve;
    public String telEleve;

    public EditText etPrenomEleve;
    public EditText etNomEleve;
    public EditText etAdresseEleve;
    public EditText etCpEleve;
    public EditText etVilleEleve;
    public EditText etTelEleve;

    public EleveModifierFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_eleve_modifier, container, false);

        etPrenomEleve = (EditText) layout.findViewById(R.id.etPrenomEleveModif);
        etNomEleve = (EditText) layout.findViewById(R.id.etNomEleveModif);
        etAdresseEleve = (EditText) layout.findViewById(R.id.etAdresseEleveModif);
        etCpEleve = (EditText) layout.findViewById(R.id.etCpEleveModif);
        etVilleEleve = (EditText) layout.findViewById(R.id.etVilleEleveModif);
        etTelEleve = (EditText) layout.findViewById(R.id.etTelEleveModif);


        prenomEleve = Eleve.eleveSelect.getPrenomEleve();
        if (prenomEleve == null){
            etPrenomEleve.setText("");
        } else {etPrenomEleve.setText(prenomEleve);}

        nomEleve = Eleve.eleveSelect.getNomEleve();
        if (nomEleve == null){
            etNomEleve.setText("");
        } else {etNomEleve.setText(nomEleve);}

        adresseEleve = Eleve.eleveSelect.getAdresseEleve();
        if (adresseEleve == null){
            etAdresseEleve.setText("");
        } else {etAdresseEleve.setText(adresseEleve);}

        cpEleve = Eleve.eleveSelect.getCpEleve();
        if (cpEleve == null){
            etCpEleve.setText("");
        }else {etCpEleve.setText(cpEleve);}

        villeEleve = Eleve.eleveSelect.getVilleEleve();
        if (villeEleve == null){
            etVilleEleve.setText("");
        } else {etVilleEleve.setText(villeEleve);}

        telEleve = Eleve.eleveSelect.getTelEleve();
        if (telEleve == null){
            etTelEleve.setText("");
        } else {etTelEleve.setText(telEleve);}

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_eleve_modifier, menu);
        NavigationDrawerActivity.actionBar.setTitle("Modifier");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_annuler_modifier:
                Toast.makeText(getActivity(), "Annulé", Toast.LENGTH_SHORT).show();
                etPrenomEleve.setText(null);
                etNomEleve.setText(null);
                etAdresseEleve.setText(null);
                etCpEleve.setText(null);
                etVilleEleve.setText(null);
                etTelEleve.setText(null);

                Fragment frgListeEleveAnnule;
                frgListeEleveAnnule = new EleveFicheFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgListeEleveAnnule)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_valider_modifier:
                prenomEleve = etPrenomEleve.getText().toString();
                nomEleve = etNomEleve.getText().toString();
                adresseEleve = etAdresseEleve.getText().toString();
                cpEleve = etCpEleve.getText().toString();
                villeEleve = etVilleEleve.getText().toString();
                telEleve = etTelEleve.getText().toString();

                Eleve.eleveSelect = new Eleve(prenomEleve, nomEleve, adresseEleve, cpEleve, villeEleve, telEleve);

                Toast.makeText(getActivity(), "Enregistrer " + Eleve.eleveSelect.toString(), Toast.LENGTH_SHORT).show();

                Fragment frgListeEleveValide;
                frgListeEleveValide = new EleveFicheFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgListeEleveValide)
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
