package fr.damienseyve.mapeleve;

import com.google.api.services.calendar.model.CalendarList;

import java.io.IOException;


public class AsyncLoadCalendars extends CalendarAsyncTask {

    AsyncLoadCalendars(ListePlanningFragment calendarSample) {
        super(calendarSample);
    }

    @Override
    protected void doInBackground() throws IOException {
        CalendarList feed = client.calendarList().list().setFields(CalendarInfo.FEED_FIELDS).execute();
        model.reset(feed.getItems());
    }

    static void run(ListePlanningFragment calendarSample) {
        new AsyncLoadCalendars(calendarSample).execute();
    }
}