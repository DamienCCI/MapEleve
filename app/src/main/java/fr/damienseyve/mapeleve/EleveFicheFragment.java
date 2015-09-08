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
import android.widget.TextView;


public class EleveFicheFragment extends Fragment {

    public String prenomEleve;
    public String nomEleve;
    public String adresseEleve;
    public String cpEleve;
    public String villeEleve;
    public String telEleve;

    public TextView tvPrenomEleve;
    public TextView tvNomEleve;
    public TextView tvAdresseEleve;
    public TextView tvCpEleve;
    public TextView tvVilleEleve;
    public TextView tvTelEleve;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_eleve_fiche, container, false);

        prenomEleve = Eleve.eleveSelect.getPrenomEleve();
        nomEleve = Eleve.eleveSelect.getNomEleve();
        adresseEleve = Eleve.eleveSelect.getAdresseEleve();
        cpEleve = Eleve.eleveSelect.getCpEleve();
        villeEleve = Eleve.eleveSelect.getVilleEleve();
        telEleve = Eleve.eleveSelect.getTelEleve();

        tvPrenomEleve = (TextView) layout.findViewById(R.id.tvPrenomEleve);
        tvNomEleve = (TextView) layout.findViewById(R.id.tvNomEleve);
        tvAdresseEleve = (TextView) layout.findViewById(R.id.tvAdresseEleve);
        tvCpEleve = (TextView) layout.findViewById(R.id.tvCpEleve);
        tvVilleEleve = (TextView) layout.findViewById(R.id.tvVilleEleve);
        tvTelEleve = (TextView) layout.findViewById(R.id.tvTelEleve);

        tvPrenomEleve.setText(prenomEleve);
        tvNomEleve.setText(nomEleve);
        tvAdresseEleve.setText(adresseEleve);
        tvCpEleve.setText(cpEleve);
        tvVilleEleve.setText(villeEleve);
        tvTelEleve.setText(telEleve);

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_eleve_fiche, menu);
        NavigationDrawerActivity.actionBar.setTitle(Eleve.eleveSelect.getPrenomEleve() + " " +
                Eleve.eleveSelect.getNomEleve());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_editer:
                Fragment frgEleveModif;
                frgEleveModif = new EleveModifierFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgEleveModif)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_historique:
                Fragment frgEleveLeconHisto;
                frgEleveLeconHisto = new LeconHistoriqueFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgEleveLeconHisto)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_nouvelle_lecon:
                Fragment frgEleveLecon;
                frgEleveLecon = new LeconFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgEleveLecon)
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
