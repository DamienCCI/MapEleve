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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class LeconFragment extends Fragment implements OnMapReadyCallback{

    MapView gMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lecon, container, false);

        gMapView = (MapView) layout.findViewById(R.id.mapViewLecon);
        gMapView.onCreate(savedInstanceState);
        gMapView.getMapAsync(this);

        // Initialise la carte.
        MapsInitializer.initialize(this.getActivity());

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lecon, menu);
        NavigationDrawerActivity.actionBar.setTitle("Nouvelle leçon");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.action_annuler_lecon:
                Fragment frgLeconAnnulee;
                frgLeconAnnulee = new EleveFicheFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgLeconAnnulee)
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(getContext(), "Leçon annulée", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_terminer_lecon:
                Fragment frgLeconTerminee;
                frgLeconTerminee = new AccueilFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgLeconTerminee)
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(getContext(), "Leçon terminée !", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng soulzmatt = new LatLng(47.9607670, 7.2388060);

        // Bouton pour repositionner la camera sur la position
        map.setMyLocationEnabled(true);

        // Vue normale
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Mouvement de camera a l'ouverture
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(soulzmatt, 13));
    }

    @Override
    public void onResume() {
        gMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        gMapView.onLowMemory();
        super.onLowMemory();
    }
}
