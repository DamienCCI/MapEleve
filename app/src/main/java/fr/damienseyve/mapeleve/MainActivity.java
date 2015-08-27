package fr.damienseyve.mapeleve;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        listeMenu = getResources().getStringArray(R.array.drawerArray);

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayoutLeft);

        drawerListView = (ListView) findViewById(R.id.listMenu);

        drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_list_drawer, listeMenu));

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                /**** Gestion des clics du menu ***/
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
                drawerLayout.closeDrawer(drawerListView);
            }
        });

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

        drawerLayout.setDrawerListener(drawerListener);
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
