package fr.damienseyve.mapeleve;


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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EleveListeFragment extends Fragment {

    ListView listView;
    List<Eleve> listEleve;
    SwipeDetector swipeDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_liste_eleve, container, false);

        listEleve = new ArrayList<>();
        Eleve eleve1 = new Eleve("50", "Cent", "12 Rue du Riesling", "68000", "NY", "06 42 23 23 34");
        listEleve.add(eleve1);
        Eleve eleve2 = new Eleve("Alice", "Liddell", "12 Rue du Riesling", "68000", "Pays des Merveilles", "06 42 23 23 34");
        listEleve.add(eleve2);
        Eleve eleve3 = new Eleve("Anne", "Hathaway", "12 Rue du Riesling", "68000", "Monaco", "06 42 23 23 34");
        listEleve.add(eleve3);
        Eleve eleve4 = new Eleve("Barney", "Stinsons", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve4);
        Eleve eleve5 = new Eleve("Bradley", "Cooper", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve5);
        Eleve eleve6 = new Eleve("Bruno", "Mars", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve6);
        Eleve eleve7 = new Eleve("Dr", "Dre", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve7);
        Eleve eleve8 = new Eleve("Kanye", "West", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve8);
        Eleve eleve9 = new Eleve("Kendrick", "Lamar", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve9);
        Eleve eleve10 = new Eleve("Kid", "Cudi", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve10);
        Eleve eleve11 = new Eleve("Robbie", "Williams", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve11);
        Eleve eleve12 = new Eleve("Snoop", "Dogg", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve12);
        Eleve eleve13 = new Eleve("Taylor", "Swift", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve13);

        Eleve eleve14 = new Eleve("50", "Cent", "12 Rue du Riesling", "68000", "NY", "06 42 23 23 34");
        listEleve.add(eleve14);
        Eleve eleve15 = new Eleve("Alice", "Liddell", "12 Rue du Riesling", "68000", "Pays des Merveilles", "06 42 23 23 34");
        listEleve.add(eleve15);
        Eleve eleve16 = new Eleve("Anne", "Hathaway", "12 Rue du Riesling", "68000", "Monaco", "06 42 23 23 34");
        listEleve.add(eleve16);
        Eleve eleve17 = new Eleve("Barney", "Stinsons", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve17);
        Eleve eleve18 = new Eleve("Bradley", "Cooper", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve18);
        Eleve eleve19 = new Eleve("Bruno", "Mars", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve19);
        Eleve eleve20 = new Eleve("Dr", "Dre", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve20);
        Eleve eleve21 = new Eleve("Kanye", "West", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve21);
        Eleve eleve22 = new Eleve("Kendrick", "Lamar", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve22);
        Eleve eleve23 = new Eleve("Kid", "Cudi", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve23);
        Eleve eleve24 = new Eleve("Robbie", "Williams", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve24);
        Eleve eleve25 = new Eleve("Snoop", "Dogg", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve25);
        Eleve eleve26 = new Eleve("Taylor", "Swift", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve26);

        Eleve eleve27 = new Eleve("50", "Cent", "12 Rue du Riesling", "68000", "NY", "06 42 23 23 34");
        listEleve.add(eleve27);
        Eleve eleve28 = new Eleve("Alice", "Liddell", "12 Rue du Riesling", "68000", "Pays des Merveilles", "06 42 23 23 34");
        listEleve.add(eleve28);
        Eleve eleve29 = new Eleve("Anne", "Hathaway", "12 Rue du Riesling", "68000", "Monaco", "06 42 23 23 34");
        listEleve.add(eleve29);
        Eleve eleve30 = new Eleve("Barney", "Stinsons", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve30);
        Eleve eleve31 = new Eleve("Bradley", "Cooper", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve31);
        Eleve eleve32 = new Eleve("Bruno", "Mars", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve32);
        Eleve eleve33 = new Eleve("Dr", "Dre", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve33);
        Eleve eleve34 = new Eleve("Kanye", "West", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve34);
        Eleve eleve35 = new Eleve("Kendrick", "Lamar", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve35);
        Eleve eleve36 = new Eleve("Kid", "Cudi", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve36);
        Eleve eleve37 = new Eleve("Robbie", "Williams", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve37);
        Eleve eleve38 = new Eleve("Snoop", "Dogg", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve38);
        Eleve eleve39 = new Eleve("Taylor", "Swift", "12 Rue du Riesling", "68000", "Colmar", "06 42 23 23 34");
        listEleve.add(eleve39);

        listView = (ListView) layout.findViewById(R.id.lvListeEleve);

        EleveListAdapter eleveListAdapter = new EleveListAdapter(getContext(), listEleve);
        listView.setAdapter(eleveListAdapter);

        swipeDetector = new SwipeDetector();
        listView.setOnTouchListener(swipeDetector);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Eleve.eleveSelect = (Eleve) o;

                swipeDetector.swipeDetected();
                if (swipeDetector.getAction() == SwipeDetector.Action.RL) {
                    Toast.makeText(getContext(), "Voulez-vous vraiment supprimer cet élève ?", Toast.LENGTH_SHORT).show();
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_ajouter) {
            Toast.makeText(getActivity(), "Ajouter", Toast.LENGTH_SHORT).show();
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
