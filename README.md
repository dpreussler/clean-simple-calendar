# clean-simple-calendar
Small simple android calendar implementation

Time-Square is the ultimate calendar but a but hard to customize
The alternative Caldroid is based on really unclean code, hard to change, hard to test.
Also it is used custom selection logic instead of using selection and disabled states of the views.


This one is
* a simple view, not a fragment
* item layout xml provided by developer, not incliuded
* supports adding custom view holder and adapters but not needed.
* no multiselect
* supports i8n by different starting days of week
* no right-to-left for now
It is just the month widget, no navigation or similar.


Quick start:
============
Add the 'CalendarWidget' to your layout.
Call 

```java
calendarWidget.set(
	new CalendarBuilder(R.layout.calendar_item_layout, R.layout.calendar_header_layout));
```

or set more implicit the shown month:
```java
calendarWidget.set(
CalendarDataFactory.getInstance(Locale.getDefault()).create(date, ROWS),
	new CalendarBuilder(R.layout.calendar_item_layout, R.layout.calendar_header_layout));
```
where date is a date for the month to show and ROWS an int naming the number of rows to be shown (6 is default, 4 should be minimum)



done.

The layouts must have the following items:

the header layout (day names) must contain one textView with id 'R.id.day' (see the 'ids.xml')
the content (day numbers) must contain:
a 'TextView' with id 'R.id.date_text'
and any kind of 'View' with id 'R.id.custom_cell' that will be used to set selected state (and disabled state for start/end of non-current month date)


example:
```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@id/custom_cell"
    android:selectable="true"
    android:background="@drawable/custom_calendar_bg_selector"
	android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@id/date_text"
        android:duplicateParentState="true"
		android:layout_width="wrap_parent"
	    android:layout_height="wrap_parent"/>
</RelativeLayout>
```
