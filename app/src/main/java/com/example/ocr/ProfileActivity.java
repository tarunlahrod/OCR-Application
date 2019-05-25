package com.example.ocr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        mAuth = FirebaseAuth.getInstance();

        tvEmail = findViewById(R.id.textView3);
        tvEmail.setText(getIntent().getExtras().getString("Name"));
    }

    public void onClickLogout(View view) {
        mAuth.signOut();
        Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
