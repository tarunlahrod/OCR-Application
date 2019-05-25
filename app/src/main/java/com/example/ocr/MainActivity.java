package com.example.ocr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText etemail, etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);
        setTitle("Login");
        etemail = findViewById(R.id.editText);
        etpassword = findViewById(R.id.editText2);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    public void onClickSignIn(View view) {

        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(this,"Please wait...","Processing...",true);
            (mAuth.signInWithEmailAndPassword(email,password))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        i.putExtra("Email", mAuth.getCurrentUser().getEmail());
                        i.putExtra("Name", mAuth.getCurrentUser().getDisplayName());
                        startActivity(i);
                        finish();
                    }
                    else {
                        Log.e("Error",task.getException().toString());
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void onClickSignUp(View view) {
        Intent i = new Intent(getApplicationContext(), PopActivity.class);
        startActivity(i);
        finish();
    }
}
