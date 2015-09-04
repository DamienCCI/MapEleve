package fr.damienseyve.mapeleve;


import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

/**
 * Tâche asynchrone qui s'occupe de l'affichage du progrès, des autorisations,
 * de la gestion des exceptions, et de la notif quand lorsque l'opération a réussi.
 */

abstract class CalendarAsyncTask extends AsyncTask<Void, Void, Boolean> {

    final ListePlanningFragment activity;
    final CalendarModel model;
    final com.google.api.services.calendar.Calendar client;
    //private final View progressBar;

    CalendarAsyncTask(ListePlanningFragment activity) {
        this.activity = activity;
        model = activity.model;
        client = activity.client;
        //progressBar = activity.findViewById(R.id.title_refresh_progress);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.numAsyncTasks++;
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected final Boolean doInBackground(Void... ignored) {
        try {
            doInBackground();
            return true;
        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            activity.showGooglePlayServicesAvailabilityErrorDialog(
                    availabilityException.getConnectionStatusCode());
        } catch (UserRecoverableAuthIOException userRecoverableException) {
            activity.startActivityForResult(
                    userRecoverableException.getIntent(), ListePlanningFragment.REQUEST_AUTHORIZATION);
        } catch (IOException e) {
            activity.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity.getActivity(), "Pas de connexion Internet, pas de planning", Toast.LENGTH_LONG).show();
                }
            });
        }
        return false;
    }

    @Override
    protected final void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        if (0 == --activity.numAsyncTasks) {
            //progressBar.setVisibility(View.GONE);
        }
        if (success) {
            activity.refreshView();
        }
    }

    abstract protected void doInBackground() throws IOException;
}