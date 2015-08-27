package fr.damienseyve.mapeleve;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

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
                        Toast.makeText(ctx, "Liste des élèves", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(ctx, "Planning", Toast.LENGTH_LONG).show();
                        break;
                }
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
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    public MainActivity getMainActivity(){
        return this;
    }
}
