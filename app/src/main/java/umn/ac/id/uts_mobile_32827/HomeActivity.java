package umn.ac.id.uts_mobile_32827;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static umn.ac.id.uts_mobile_32827.Data.songs;
import static umn.ac.id.uts_mobile_32827.Data.showModal;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Summons an alert.
        if (showModal) {
            welcomeDialog();
            showModal = false;
        }

        RecyclerView musicList = findViewById(R.id.musicList);

        // Place music inside RecyclerView.
        MusicAdapter musicAdapter = new MusicAdapter(this, songs);
        musicList.setAdapter(musicAdapter);
        musicList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profileMenu) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

            return true;
        } else if (item.getItemId() == R.id.logoutMenu){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void welcomeDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Welcome!");
        alert.setMessage("Nicholas Dwiarto Wirasbawa\n00000032827");

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.show();
    }
}