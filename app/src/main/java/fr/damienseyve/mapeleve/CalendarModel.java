package fr.damienseyve.mapeleve;


import com.google.api.services.calendar.model.CalendarListEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Modèle de Thread-safe pour Google Calendrier */
public class CalendarModel {

    private final Map<String, CalendarInfo> calendars = new HashMap<String, CalendarInfo>();

    int size() {
        synchronized (calendars) {
            return calendars.size();
        }
    }

    CalendarInfo get(String id) {
        synchronized (calendars) {
            return calendars.get(id);
        }
    }

    void add(CalendarListEntry calendarToAdd) {
        synchronized (calendars) {
            CalendarInfo found = get(calendarToAdd.getId());
            if (found == null) {
                calendars.put(calendarToAdd.getId(), new CalendarInfo(calendarToAdd));
            } else {
                found.update(calendarToAdd);
            }
        }
    }

    void reset(List<CalendarListEntry> calendarsToAdd) {
        synchronized (calendars) {
            calendars.clear();
            for (CalendarListEntry calendarToAdd : calendarsToAdd) {
                add(calendarToAdd);
            }
        }
    }

    public CalendarInfo[] toSortedArray() {
        synchronized (calendars) {
            List<CalendarInfo> result = new ArrayList<CalendarInfo>();
            for (CalendarInfo calendar : calendars.values()) {
                result.add(calendar.clone());
            }
            Collections.sort(result);
            return result.toArray(new CalendarInfo[0]);
        }
    }
}
