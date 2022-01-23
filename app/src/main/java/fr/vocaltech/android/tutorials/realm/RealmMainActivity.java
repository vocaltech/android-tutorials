package fr.vocaltech.android.tutorials.realm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.bson.types.ObjectId;

import fr.vocaltech.android.tutorials.realm.Position;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import fr.vocaltech.android.tutorials.databinding.ActivityMainRealmBinding;

public class RealmMainActivity extends AppCompatActivity {
    private static final String TAG = "RealmMainActivity";
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityMainRealmBinding binding;

        super.onCreate(savedInstanceState);

        // UI
        binding = ActivityMainRealmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // realm
        mRealm = Realm.getDefaultInstance();

        Realm.getInstanceAsync(Realm.getDefaultConfiguration(), new Realm.Callback() {
            @Override
            public void onSuccess(@NonNull Realm realm) {
                Log.d(TAG, "onSuccess: Successfully fetched realm instance !");
            }

            @Override
            public void onError(@NonNull Throwable exception) {
                super.onError(exception);
            }
        });

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Position position = new Position();
                position.setId(new ObjectId().toString());
                position.setLatitude(45.4);
                position.setLongitude(1.5);
                realm.insert(position);
            }
        });

        RealmResults<Position> positions = mRealm.where(Position.class).findAllAsync();
        // length of items is zero when initially returned
        positions.addChangeListener(new RealmChangeListener<>() {
            @Override
            public void onChange(@NonNull RealmResults<Position> _positions) {
                // items results now contains all matched objects (more than zero)
                Log.d(TAG, "onChange: positions: " + _positions);

                StringBuilder posResult = new StringBuilder();
                for (Position position: positions) {
                    posResult.append(position);
                    posResult.append("\n");
                    binding.result.setText(posResult);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mRealm != null) {
            mRealm.removeAllChangeListeners();
            mRealm.close();
        }
    }
}
