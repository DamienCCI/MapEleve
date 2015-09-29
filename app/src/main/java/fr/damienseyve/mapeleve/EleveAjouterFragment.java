package fr.damienseyve.mapeleve;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class EleveAjouterFragment extends Fragment{

    public EleveAjouterFragment() {
        super();
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_eleve_ajouter, container, false);

        etPrenomEleve = (EditText) layout.findViewById(R.id.etPrenomEleveAjout);
        etNomEleve = (EditText) layout.findViewById(R.id.etNomEleveAjout);
        etAdresseEleve = (EditText) layout.findViewById(R.id.etAdresseEleveAjout);
        etCpEleve = (EditText) layout.findViewById(R.id.etCpEleveAjout);
        etVilleEleve = (EditText) layout.findViewById(R.id.etVilleEleveAjout);
        etTelEleve = (EditText) layout.findViewById(R.id.etTelEleveAjout);

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_eleve_ajout, menu);
        NavigationDrawerActivity.actionBar.setTitle("Nouvel élève");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_annuler_ajout:
                etPrenomEleve.setText(null);
                etNomEleve.setText(null);
                etAdresseEleve.setText(null);
                etCpEleve.setText(null);
                etVilleEleve.setText(null);
                etTelEleve.setText(null);

                Toast.makeText(getActivity(), "Annulée", Toast.LENGTH_SHORT).show();

                Fragment frgListeEleveAnnule;
                frgListeEleveAnnule = new EleveListeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgListeEleveAnnule)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_valider_ajout:
                prenomEleve = etPrenomEleve.getText().toString();
                nomEleve = etNomEleve.getText().toString();
                adresseEleve = etAdresseEleve.getText().toString();
                cpEleve = etCpEleve.getText().toString();
                villeEleve = etVilleEleve.getText().toString();
                telEleve = etTelEleve.getText().toString();

                EleveManip eleveManip = new EleveManip(getContext());
                eleveManip.open();
                Eleve newEleve = new Eleve(eleveManip.getLastID(),prenomEleve, nomEleve, adresseEleve, cpEleve, villeEleve, telEleve);
                eleveManip.insertEleve(newEleve);
                eleveManip.close();

                Toast.makeText(getActivity(), "Enregistrer", Toast.LENGTH_SHORT).show();

                Fragment frgListeEleveValide;
                frgListeEleveValide = new EleveListeFragment();
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
