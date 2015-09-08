package fr.damienseyve.mapeleve;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class LeconHistoriqueFragment extends Fragment implements OnMapReadyCallback {

    MapView gMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lecon_historique, container, false);

        gMapView = (MapView) layout.findViewById(R.id.mapViewLeconHistorique);
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
        inflater.inflate(R.menu.menu_lecon_historique, menu);
        NavigationDrawerActivity.actionBar.setTitle("Historique");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng belfort = new LatLng(47.6396740, 6.8638490);

        // Bouton pour repositionner la camera sur la position
        map.setMyLocationEnabled(true);

        // Vue normale
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Mouvement de camera a l'ouverture
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(belfort, 13));
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