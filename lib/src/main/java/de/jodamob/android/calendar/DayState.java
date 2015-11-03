package de.jodamob.android.calendar;

public class DayState {

    private boolean isSelected;
    private boolean isCurrentMonth;
    private boolean isToday;

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setIsCurrentMonth(boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
    }

    public void setIsToday(boolean today) {
        this.isToday = today;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean isToday() {
        return isToday;
    }
}
