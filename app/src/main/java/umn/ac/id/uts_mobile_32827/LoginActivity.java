package umn.ac.id.uts_mobile_32827;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private final String username = "uasmobile";
    private final String password = "uasmobilegenap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        EditText usernameInput = findViewById(R.id.inputUsername);
        EditText passwordInput = findViewById(R.id.inputPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInputParsed = usernameInput.getText().toString();
                String passwordInputParsed = passwordInput.getText().toString();

                if (usernameInputParsed.equals(username) && passwordInputParsed.equals(password)) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully logged in! Please wait to be redirected!",
                            Toast.LENGTH_LONG).show();

                    // Wait 2 seconds before loading new activity.
                    new Handler().postDelayed(() -> startActivity(new Intent(getApplicationContext(), HomeActivity.class)),
                            2000);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Failed to login!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}