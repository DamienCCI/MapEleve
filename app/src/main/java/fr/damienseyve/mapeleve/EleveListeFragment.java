package fr.damienseyve.mapeleve;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class EleveListeFragment extends Fragment {

    ListView listView;
    SwipeDetector swipeDetector;
    private Uri todoUri;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_eleve_liste, container, false);


        EleveManip eleveManip = new EleveManip(getContext());

        eleveManip.open();

        // Création et insertion d'un contact
        Eleve eleve = new Eleve(0, "Damien", "SEYVE", "12 Rue du Riesling", "68000", "Colmar", "08 00 00 00 00");
        eleveManip.insertEleve(eleve);

        // Récupération du contact
        Eleve eleveFromBdd = eleveManip.getFirstContactWithNumeroTelephone("066045");
        // Si le contact à bien été ajouté à la BDD, on affiche les données du
        // contact dans un Toast et on modifie son numéro de téléphone dans la
        // BDD
        if (eleveFromBdd != null) {
            Toast.makeText(getContext(), eleveFromBdd.toString(), Toast.LENGTH_LONG).show();
            eleveFromBdd.setTelEleve("08 00 00 00 00");
            eleveManip.updateContact(eleveFromBdd.getId(), eleveFromBdd);
        }

        // Récupération du contact grâce au nouveau numéro de téléphone
        eleveFromBdd = eleveManip.getFirstContactWithNumeroTelephone("08 00 00 00 00");
        // S'il existe un contact possédant ce numéro dans la BDD, alors on
        // affiche ses données dans un Toast et on le supprime de la base de
        // données
        if (eleveFromBdd != null) {
            Toast.makeText(getContext(), eleveFromBdd.toString(), Toast.LENGTH_LONG)
                    .show();
            eleveManip.removeContactWithID(eleveFromBdd.getId());
        }

        eleveManip.close();

        //http://www.vogella.com/tutorials/AndroidSQLite/article.html


        listView = (ListView) layout.findViewById(R.id.lvListeEleve);

        //EleveListAdapter eleveListAdapter = new EleveListAdapter(getContext(), values);
        //listView.setAdapter(eleveListAdapter);

        swipeDetector = new SwipeDetector();
        listView.setOnTouchListener(swipeDetector);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Eleve.eleveSelect = (Eleve) o;

                swipeDetector.swipeDetected();
                if (swipeDetector.getAction() == SwipeDetector.Action.RL) {
                    Dialog d = new AlertDialog.Builder(getActivity())
                            .setTitle("Suppression")
                            .setMessage("Voulez-vous vraiment supprimer cet élève ?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "Suppression de l'élève...", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    d.setOwnerActivity(getActivity());
                    d.show();
                } else {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment frgEleve;
                    frgEleve = new EleveFicheFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, frgEleve)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
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
        inflater.inflate(R.menu.menu_eleve_liste, menu);
        NavigationDrawerActivity.actionBar.setTitle("Liste des élèves");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_ajouter) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment frgEleveAjout;
            frgEleveAjout = new EleveAjouterFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, frgEleveAjout)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        //datasource.open();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        //datasource.close();
        super.onDestroy();
    }

    @Override
    public  void onPause() {
        //datasource.close();
        super.onPause();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
