package fr.vocaltech.android.tutorials;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        // Realm init
        Realm.init(getApplicationContext());
        String realmName = "Realm_tutorial.realm";

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(realmName)
                .build();

        Realm.setDefaultConfiguration(config);

        Log.d(TAG, "onCreate: Realm path: " + config.getPath());
    }
}
