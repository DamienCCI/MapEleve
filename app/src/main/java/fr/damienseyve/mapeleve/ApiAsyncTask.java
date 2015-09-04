package fr.damienseyve.mapeleve;


import android.os.AsyncTask;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An asynchronous task that handles the Google Calendar API call.
 * Placing the API calls in their own task ensures the UI stays responsive.
 */
public class ApiAsyncTask extends AsyncTask<Void, Void, Void> {

    private PlanningFragment planningFragment;


    /**
     * Constructor.
     * @param activity MainActivity that spawned this task.
     */
    ApiAsyncTask(PlanningFragment activity) {
        this.planningFragment = activity;
    }

    /**
     * Background task to call Google Calendar API.
     * @param params no parameters needed for this task.
     */
    @Override
    protected Void doInBackground(Void... params) {
        try {
            planningFragment.clearResultsText();
            planningFragment.updateResultsText(getDataFromApi());

        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            planningFragment.showGooglePlayServicesAvailabilityErrorDialog(
                    availabilityException.getConnectionStatusCode());

        } catch (UserRecoverableAuthIOException userRecoverableException) {
            planningFragment.startActivityForResult(
                    userRecoverableException.getIntent(),
                    planningFragment.REQUEST_AUTHORIZATION);

        } catch (Exception e) {
            planningFragment.updateStatus("L'erreur suivante c'est produit :\n" +
                    e.getMessage());
        }
        return null;
    }

    /**
     * Fetch a list of the next 100 events from the primary calendar.
     * @return List of Strings describing returned events.
     * @throws IOException
     */
    private List<Planning> getDataFromApi() throws IOException, ParseException {
        // Liste les 10 prochains événements du calendrier principal.
        DateTime now = new DateTime(System.currentTimeMillis());
        List<Planning> eventStrings = new ArrayList<Planning>();
        Events events = planningFragment.mService.events().list(CalendarInfo.calendarInfoSelect.id)
                .setMaxResults(100)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();


        for (Event event : items)
        {
            DateTime date = event.getStart().getDateTime();
            if (date == null) {
                // All-day events don't have start times, so just use the start date.
                date = event.getStart().getDate();
            }
            SimpleDateFormat dateStart = new SimpleDateFormat ("dd/MM/yyyy", Locale.FRANCE);
            String startString = dateStart.format(date.getValue());


            DateTime heureDebDateTime = event.getStart().getDateTime();
            if (heureDebDateTime == null) {
                // All-day events don't have start times, so just use the start date.
                heureDebDateTime = event.getStart().getDate();
            }
            SimpleDateFormat heureDeb = new SimpleDateFormat ("HH:mm", Locale.FRANCE);
            String heureDebString = heureDeb.format(heureDebDateTime.getValue());


            DateTime heureFinDateTime = event.getEnd().getDateTime();
            if (heureFinDateTime == null) {
                // All-day events don't have start times, so just use the start date.
                heureFinDateTime = event.getStart().getDate();
            }
            SimpleDateFormat heureFin = new SimpleDateFormat ("HH:mm", Locale.FRANCE);
            String heureFinString = heureFin.format(heureFinDateTime.getValue());

            String summary = event.getSummary();

            String lieux = event.getLocation();
            if (lieux == null){
                lieux = "Domicile";
            }

            Planning p = new Planning(startString, heureDebString, heureFinString, summary, lieux);
            eventStrings.add(p);
        }
        return eventStrings;
    }
}