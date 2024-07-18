package com.aymen.enchere.notification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    public void configureFirebaseConnection() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("classpath:config/enchereanonyme-firebase-adminsdk-1x3zz-2554cdaf26.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
