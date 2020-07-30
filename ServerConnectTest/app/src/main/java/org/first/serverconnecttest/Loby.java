package org.first.serverconnecttest;

import android.text.Editable;

public class Loby {
    String title;
    String hostName;
    String openLink;
    Double latitude;
    Double longitude;
    String meetingDate;

    public Loby(String title, String hostName, String openLink, Double latitude, Double longitude, String meetingDate) {
        this.title = title;
        this.hostName = hostName;
        this.openLink = openLink;
        this.latitude = latitude;
        this.longitude = longitude;
        this.meetingDate = meetingDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOpenLink() {
        return openLink;
    }

    public void setOpenLink(String openLink) {
        this.openLink = openLink;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }
}
