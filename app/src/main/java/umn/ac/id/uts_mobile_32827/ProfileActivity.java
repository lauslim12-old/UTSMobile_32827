package umn.ac.id.uts_mobile_32827;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {
    private ImageView github, linkedin, stackoverflow, website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        github = findViewById(R.id.nicholas_github);
        linkedin = findViewById(R.id.nicholas_linkedin);
        stackoverflow = findViewById(R.id.nicholas_stackoverflow);
        website = findViewById(R.id.nicholas_website);

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/lauslim12")));
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.linkedin.com/in/nicholasdwiarto/")));
            }
        });

        stackoverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://stackoverflow.com/users/13980107/nicholas-d")));
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nicholasdw.com/")));
            }
        });
    }
}