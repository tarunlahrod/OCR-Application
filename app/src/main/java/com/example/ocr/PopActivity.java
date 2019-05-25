package com.example.ocr;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PopActivity extends Activity {


    private FirebaseAuth mAuth;

    EditText etemail, etpassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        mAuth = FirebaseAuth.getInstance();

        etemail = findViewById(R.id.editText3);
        etpassword = findViewById(R.id.editText4);
        btnRegister =findViewById(R.id.button2);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout((int)(width*.8),(int)(height*.7));
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.x = 0;
//        params.y = -20;
//
//        getWindow().setAttributes(params);
    }

    public void onClickRegister(View view) {
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(this,"Please wait...","Processing...",true);
            (mAuth.createUserWithEmailAndPassword(email, password))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Registration successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Log.e("ERROR", task.getException().toString());
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
    }
}
