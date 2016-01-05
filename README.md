[![Build Status](https://travis-ci.org/dpreussler/clean-simple-calendar.svg?branch=master)](https://travis-ci.org/dpreussler/clean-simple-calendar)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/clean-simple-calendar/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/clean-simple-calendar)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-clean--simple--calendar-green.svg?style=true)](https://android-arsenal.com/details/1/2946)
[![](https://img.shields.io/badge/AndroidWeekly-%23185-green.svg)](http://androidweekly.net/issues/issue-185)
# clean-simple-calendar
Small simple android calendar implementation

Time-Square is the ultimate calendar but a but hard to customize
The alternative Caldroid is based on really unclean code, hard to change, hard to test.
Also it is used custom selection logic instead of using selection and disabled states of the views.


This one is
* a simple view, not a fragment
* item layout xml provided by developer, not included
* supports i8n by different starting days of week
* support selection (style via xml selected state)
* shows last/first days of surrounding months where needed (row count configurable, style via xml disabled-state)
* marks today
* supports adding custom view holder and adapters but not needed (see styled_sample)
* no multiselect
* no right-to-left (in progress)

It is just the month widget, no navigation or similar.


```groovy
testCompile 'de.jodamob.android:clean-simple-calendar:1.0'
 
```


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

Screenshot from simple_sample (US Locale)
![alt tag](https://cloud.githubusercontent.com/assets/2426606/11047357/425b4ffe-8732-11e5-984c-b2154e3f4030.png)

Screenshot from simple_sample (GERMAN Locale)
![alt tag](https://cloud.githubusercontent.com/assets/2426606/11047490/003d72ae-8733-11e5-80b6-6506a55bd2ca.png)

Screenshot from styled_sample with custom fields and selection
![alt tag](https://cloud.githubusercontent.com/assets/2426606/11048790/2df3624c-873a-11e5-8763-0e999b768409.png)

Screenshot from simple_sample (Hebrew language)
![alt tag](https://cloud.githubusercontent.com/assets/2426606/11114236/73b46a84-8923-11e5-99ce-dcfa18c9bf4e.png)




Licensed under MIT license
(c) 2015 Danny Preussler
