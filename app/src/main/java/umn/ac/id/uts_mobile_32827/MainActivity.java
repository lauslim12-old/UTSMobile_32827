package umn.ac.id.uts_mobile_32827;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static umn.ac.id.uts_mobile_32827.Data.songs;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_FILE = 1;
    private Button loginButton, profileButton;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.mainLayout);
        showMusicPreview();

        profileButton = findViewById(R.id.profileButton);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
    }

    public ArrayList<Audio> getAllAudioFromDevice(final Context context) {
        final ArrayList<Audio> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION,
        };
        Cursor c = context.getContentResolver().query(uri,
                projection,
                null,
                null,
                null);

        if (c != null) {
            while (c.moveToNext()) {
                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);
                String duration = c.getString(3);

                String name = path.substring(path.lastIndexOf("/") + 1);

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Duration :" + duration);

                tempAudioList.add(new Audio(path, name, album, artist, duration));
            }
            c.close();
        }

        return tempAudioList;
    }

    private void showMusicPreview() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(mLayout, "Welcome to the app!", Snackbar.LENGTH_SHORT).show();
            songs = getAllAudioFromDevice(getApplicationContext());
        } else {
            requestFilePermission();
        }
    }

    private void requestFilePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Snackbar.make(mLayout, "We need file access!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    PERMISSION_REQUEST_WRITE_EXTERNAL_FILE);
                        }
                    }).show();
            songs = getAllAudioFromDevice(getApplicationContext());
        } else {
            Snackbar.make(mLayout, "Please allow permissions to use this app!", Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE_EXTERNAL_FILE);
            songs = getAllAudioFromDevice(getApplicationContext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_FILE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(mLayout, "Yay! Let's play your favorite songs!", Snackbar.LENGTH_SHORT).show();
                songs = getAllAudioFromDevice(getApplicationContext());
            } else {
                Snackbar.make(mLayout, "You have to allow permissions to use this app.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}