package fr.damienseyve.mapeleve;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class AccueilFragment extends Fragment implements OnMapReadyCallback {

    MapView gMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Récupération de l'élément MapView
        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        gMapView = (MapView) layout.findViewById(R.id.mapview);
        gMapView.onCreate(savedInstanceState);
        gMapView.getMapAsync(this);

        // Initialise la carte.
        MapsInitializer.initialize(this.getActivity());

        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        NavigationDrawerActivity.actionBar.setTitle("Accueil");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng colmar = new LatLng(48.0783953, 7.3516607);

        // Bouton pour repositionner la camera sur la position
        map.setMyLocationEnabled(true);

        // Vue normale
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Mouvement de camera a l'ouverture
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(colmar, 13));
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
