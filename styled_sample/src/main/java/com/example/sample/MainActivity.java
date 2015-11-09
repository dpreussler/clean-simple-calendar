package com.example.sample;

import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.jodamob.android.calendar.CalendarDataFactory;
import de.jodamob.android.calendar.CalenderWidget;
import de.jodamob.android.calendar.VisibleMonths;

import static com.example.sample.Celebraties.getFebruaryBirthdays;

public class MainActivity extends Activity {

    public static final int ROWS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalenderWidget widget = (CalenderWidget) findViewById(R.id.calendar);
        widget.set(getUsOnlyVersionOfFeb2015(), new StyledCalendarBuilder(getFebruaryBirthdays()));
    }

    private VisibleMonths getUsOnlyVersionOfFeb2015() {
        return CalendarDataFactory.getInstance(Locale.US).create(getFeb2015(), ROWS);
    }

    private Date getFeb2015() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 1, 1);  // set to feb 2015, a short month, 4 rows enough
        return calendar.getTime();
    }
}
