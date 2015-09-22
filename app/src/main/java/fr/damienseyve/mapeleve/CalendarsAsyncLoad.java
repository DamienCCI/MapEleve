package fr.damienseyve.mapeleve;

import com.google.api.services.calendar.model.CalendarList;

import java.io.IOException;


public class CalendarsAsyncLoad extends CalendarAsyncTask {


    // Cette classe permet de récupérer la liste des calendriers à partir du compte sélectionner
    CalendarsAsyncLoad(PlanningListeFragment calendarSample) {
        super(calendarSample);
    }

    @Override
    protected void doInBackground() throws IOException {
        CalendarList feed = client.calendarList().list().setFields(CalendarInfo.FEED_FIELDS).execute();
        model.reset(feed.getItems());
    }

    static void run(PlanningListeFragment calendarSample) {
        new CalendarsAsyncLoad(calendarSample).execute();
    }
}