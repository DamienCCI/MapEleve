package fr.damienseyve.mapeleve;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;


public class EleveListeFragment extends Fragment {

    ListView listView;
    SwipeDetector swipeDetector;
    private Uri todoUri;
    EleveManip eleveManip;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_eleve_liste, container, false);
        NavigationDrawerActivity.actionBar.setTitle("Liste des élèves");
        eleveManip = new EleveManip(getContext());
        //r&écupération liste d'éleves
        eleveManip.open();
        ArrayList<Eleve> eleves = eleveManip.getAllEleves();
        eleveManip.close();
        //http://www.vogella.com/tutorials/AndroidSQLite/article.html


        listView = (ListView) layout.findViewById(R.id.lvListeEleve);

        final EleveListAdapter eleveListAdapter = new EleveListAdapter(getContext(), eleves);
        listView.setAdapter(eleveListAdapter);

        swipeDetector = new SwipeDetector();
        listView.setOnTouchListener(swipeDetector);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

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
                                    eleveManip.open();
                                    eleveManip.removeEleveWithID(Eleve.eleveSelect.getId());
                                    eleveManip.close();
                                    eleveListAdapter.removeAt(position);
                                    Toast.makeText(getContext(), "Suppression de l'élève "+ Eleve.eleveSelect.getNomEleve(), Toast.LENGTH_SHORT).show();
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
        //NavigationDrawerActivity.actionBar.setTitle("Liste des élèves");
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
