package com.vb.ups.objects;

public class Notification{
    private String title;
    private String description;
    private String userID;
    private String time;

    public Notification() {

    }

    public Notification(String time, String userID, String title, String description) {
        this.time = time;
        this.userID = userID;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userID='" + userID + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return time != null ? time.equals(that.time) : that.time == null;

    }
}
