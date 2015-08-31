package fr.damienseyve.mapeleve;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private String[] listeMenu;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerListener;
    private MainActivity mainActivity;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = getMainActivity();

        // Création de la variable ctx dans le but de réduire un maximun le nombre d'appel
        // de la méthode getApplicationContext
        final Context ctx = getApplicationContext();

        // Toolbar en haut qui contient le bouton pour déployer le drawer et les futurs boutons
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Récupération du tableau de Stringqui contient la liste du Drawer
        listeMenu = getResources().getStringArray(R.array.drawerArray);

        // Récupération du Drawer en lui-même
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayoutLeft);

        // Récupération de la ListView qui contient le text récupérer plus haut
        drawerListView = (ListView) findViewById(R.id.listMenu);

        // Adapter entre le tableau de String et la ListView
        drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_list_drawer, listeMenu));

        //Gestionnaire des événements click du NavigationDrawer
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position) {
                    case 0:
                        //Création d'une nouvelle Intent pour passer sur l'activité Liste d'élève
                        Intent intentListe = new Intent(MainActivity.this, ListeEleveActivity.class);
                        // Permet de supprimer l'effet de transition de base et rends l'expérience plus fluide
                        intentListe.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentListe);
                        break;
                    case 1:
                        //Création d'une nouvelle Intent pour passer sur l'activité Planning
                        Intent intentPlanning = new Intent(MainActivity.this, PlanningActivity.class);
                        // Permet de supprimer l'effet de transition de base et rends l'expérience plus fluide
                        intentPlanning.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentPlanning);
                        break;
                }
                //Fermeture du tirroir
                drawerLayout.closeDrawer(drawerListView);
            }
        });

        // Listeneur derrière le bouton de la Toolbar qui permet l'ouverture du tirroir
        drawerListener = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawerOpen,
                R.string.drawerClose) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

        };

        // Set du Listeneur créée plus haut
        drawerLayout.setDrawerListener(drawerListener);

        // Synchronisation de l'état du tirroir
        drawerListener.syncState();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    public MainActivity getMainActivity(){
        return this;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng colmar = new LatLng(48.0833, 7.3667);

        // Bouton pour repositionner la camera sur la position
        map.setMyLocationEnabled(true);

        // Mouvement de camera a l'ouverture
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(colmar, 13));

        // Vue normale
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Vue satellite
        //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Vue normale avec les terrains d'une couleur differente + Relief
        //map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // Vue avec rien du tout
        //map.setMapType(GoogleMap.MAP_TYPE_NONE);

        // Hybrid : Vue satellite + Nom des villes et des rues
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Marqueur
        //map.addMarker(new MarkerOptions()
        //        .position(colmar)
        //        .flat(true));
    }
}
