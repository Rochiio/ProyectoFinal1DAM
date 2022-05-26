package com.example.myanimelist.dto;

public class LoadDTO {
    boolean loaded;
    boolean nightMode;

    public LoadDTO(boolean loaded, boolean nightMode) {
        this.loaded = loaded;
        this.nightMode = nightMode;
    }


    @Override
    public String toString() {
        return  "loaded=" + loaded + "\n"+
                "nightMode=" + nightMode;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isNightMode() {
        return nightMode;
    }

    public void setNightMode(boolean nightMode) {
        this.nightMode = nightMode;
    }
}
