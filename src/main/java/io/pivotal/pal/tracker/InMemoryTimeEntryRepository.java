package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    ConcurrentHashMap<Long, TimeEntry> entry = new ConcurrentHashMap<>();

    long timeEntryId;
    public TimeEntry create(TimeEntry timeEntry) {

        timeEntryId++;
        TimeEntry createdTimeEntry = new TimeEntry(timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        entry.put(timeEntryId, createdTimeEntry);
        return createdTimeEntry;
    }

    public TimeEntry find(long id) {

        return entry.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<>();

        for (Map.Entry<Long, TimeEntry> entry : entry.entrySet()) {
            String key = entry.getKey().toString();
            TimeEntry value = entry.getValue();
            timeEntryList.add(value);
        }

        return timeEntryList;
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {


        TimeEntry existingTimeEntry = find(timeEntryId);



        if (existingTimeEntry != null) {
            TimeEntry newTimeEntry = new TimeEntry(existingTimeEntry.getTimeEntryId(), timeEntry.getProjectId(),timeEntry.getUserId(),
                    timeEntry.getDate(),timeEntry.getHours());

            entry.replace(timeEntryId, newTimeEntry);

        }else{
            return null;
        }

        return entry.get(timeEntryId);
    }

    public void delete(long timeEntryId) {

        entry.remove(timeEntryId);
    }
}
