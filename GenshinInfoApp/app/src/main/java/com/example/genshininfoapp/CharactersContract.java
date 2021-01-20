package com.example.genshininfoapp;

import org.json.JSONException;

public interface CharactersContract {

    public interface View {
        void getFromAPI();
        void initHashMap() throws JSONException;
    }

    public interface Presenter {
        void retrieveResult();
        void setupListview();
    }
}
