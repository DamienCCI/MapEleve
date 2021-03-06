package fr.damienseyve.mapeleve;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


public class PlanningListeFragment extends Fragment {

    private static final Level LOGGING_LEVEL = Level.OFF;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    static final int REQUEST_AUTHORIZATION = 1;
    static final int REQUEST_ACCOUNT_PICKER = 2;
    static final String TAG = "PlanningActivity";
    final HttpTransport transport = AndroidHttp.newCompatibleTransport();
    final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    GoogleAccountCredential credential;
    CalendarModel model = new CalendarModel();
    ArrayAdapter<CalendarInfo> adapter;
    com.google.api.services.calendar.Calendar client;
    int numAsyncTasks;
    private ListView listView;
    static public TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_planning_liste, container, false);

        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);


        // Google Accounts permet de récupérer un compte Google par le biais du système lui-même
        // Le choix est enregistrer dans l'application.
        credential = GoogleAccountCredential.usingOAuth2(this.getActivity(), Collections.singleton(CalendarScopes.CALENDAR));
        SharedPreferences settings = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        credential.setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));


        // Calendar client
        client = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential).setApplicationName("Google-CalendarAndroidSample/1.0")
                .build();

        textView = (TextView) layout.findViewById(R.id.tvStatutListePlanning);
        textView.setText("Récupération des calendrier...");

        listView = (ListView) layout.findViewById(R.id.lvListePlanning);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);

                // CalendarInfoSelect permet de stocker toute les valeurs de l'objet sélectionner et d'ainsi les
                // utilisé dans le prochain fragment. C'est un objet static.
                CalendarInfo.calendarInfoSelect = (CalendarInfo) o;

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment frgPlanning;
                frgPlanning = new PlanningFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frgPlanning)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_planning_liste, menu);
        NavigationDrawerActivity.actionBar.setTitle("Liste de planning");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         return super.onOptionsItemSelected(item);
    }

    void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                        connectionStatusCode, getActivity(), REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }


    // Cette méthode permet de donner toutes les instructions que l'on veux faire exécuter quand on
    // Veux rafraîchir la vue.
    void refreshView() {
        adapter = new ArrayAdapter<CalendarInfo>(
                getActivity(), android.R.layout.simple_list_item_1, model.toSortedArray()) {

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
        textView.setText("Liste des calendriers :");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkGooglePlayServicesAvailable()) {
            haveGooglePlayServices();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    CalendarsAsyncLoad.run(this);
                } else {
                    chooseAccount();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        credential.setSelectedAccountName(accountName);
                        SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.commit();
                        CalendarsAsyncLoad.run(this);
                    }
                }
                break;
        }
    }

    /** Check that Google Play services APK is installed and up to date. */
    private boolean checkGooglePlayServicesAvailable() {
        final int connectionStatusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
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
            CalendarsAsyncLoad.run(this);
        }
    }

    private void chooseAccount() {
        startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }
}