package com.ernesto.passwordmanager.presentation.item;

public class ApplicationItem {

    private int applicationImg;

    private String applicationName;

    public ApplicationItem(int applicationImg, String applicationName) {
        this.applicationImg = applicationImg;
        this.applicationName = applicationName;
    }

    public int getApplicationImg() {
        return applicationImg;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
