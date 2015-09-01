package fr.damienseyve.mapeleve;

import android.app.Activity;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListePlanningActivity extends Activity {


    private static final Level LOGGING_LEVEL = Level.OFF;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    static final String TAG = "ListePlanningActivity";
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    static final int REQUEST_AUTHORIZATION = 1;
    static final int REQUEST_ACCOUNT_PICKER = 2;
    final HttpTransport transport = AndroidHttp.newCompatibleTransport();
    final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    GoogleAccountCredential credential;
    CalendarModel model = new CalendarModel();
    ArrayAdapter<CalendarInfo> adapter;
    com.google.api.services.calendar.Calendar client;
    int numAsyncTasks;
    private ListView listView;
    private String[] listeMenu;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerListener;
    private Toolbar toolbar;


    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_planning);

        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);
        // view and menu

        listView = (ListView) findViewById(R.id.list);
        registerForContextMenu(listView);
        // Google Accounts
        credential =
                GoogleAccountCredential.usingOAuth2(this, Collections.singleton(CalendarScopes.CALENDAR));
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        credential.setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
        // Calendar client
        client = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential).setApplicationName("Google-CalendarAndroidSample/1.0")
                .build();


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
                        Intent intentListe = new Intent(ListePlanningActivity.this, ListeEleveActivity.class);
                        // Permet de supprimer l'effet de transition de base et rends l'expérience plus fluide
                        intentListe.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentListe);
                        break;
                    case 1:
                        //Création d'une nouvelle Intent pour passer sur l'activité Planning
                        Intent intentListePlanning = new Intent(ListePlanningActivity.this, ListePlanningActivity.class);
                        // Permet de supprimer l'effet de transition de base et rends l'expérience plus fluide
                        intentListePlanning.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentListePlanning);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                CalendarInfo.calendarInfoSelect = (CalendarInfo) o;

                Intent intentPlanning = new Intent(ListePlanningActivity.this, PlanningActivity.class);
                intentPlanning.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentPlanning);
            }
        });
    }

    void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {
        runOnUiThread(new Runnable() {
            public void run() {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                        connectionStatusCode, ListePlanningActivity.this, REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }

    void refreshView() {
        adapter = new ArrayAdapter<CalendarInfo>(
                this, android.R.layout.simple_list_item_1, model.toSortedArray()) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // by default it uses toString; override to use summary instead
                TextView view = (TextView) super.getView(position, convertView, parent);
                CalendarInfo calendarInfo = getItem(position);
                view.setText(calendarInfo.summary);
                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkGooglePlayServicesAvailable()) {
            haveGooglePlayServices();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode == Activity.RESULT_OK) {
                    haveGooglePlayServices();
                } else {
                    checkGooglePlayServicesAvailable();
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == Activity.RESULT_OK) {
                    AsyncLoadCalendars.run(this);
                } else {
                    chooseAccount();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        credential.setSelectedAccountName(accountName);
                        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.commit();
                        AsyncLoadCalendars.run(this);
                    }
                }
                break;
        }
    }


    /** Check that Google Play services APK is installed and up to date. */
    private boolean checkGooglePlayServicesAvailable() {
        final int connectionStatusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        }
        return true;
    }

    private void haveGooglePlayServices() {
        // check if there is already an account selected
        if (credential.getSelectedAccountName() == null) {
            // ask user to choose account
            chooseAccount();
        } else {
            // load calendars
            AsyncLoadCalendars.run(this);
        }
    }

    private void chooseAccount() {
        startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }
}
