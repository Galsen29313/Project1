package com.gal.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gal.project.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText etFname, etLname, etPhone, etEmail, etPassword;
    String  Fname, Lname, Phone, Email, Password;
    Button btnReg;
    Boolean isVaild = true;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initViews();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        mAuth = FirebaseAuth.getInstance();



    }


    private void initViews() {
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnReg) {
            Fname = etFname.getText().toString();
            Lname = etLname.getText().toString();
            Phone = etPhone.getText().toString();
            Email = etEmail.getText().toString();
            Password = etPassword.getText().toString();
            if (!Fname.isEmpty() && !Lname.isEmpty() && !Phone.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
                etFname.setText("");
                etLname.setText("");
                etPhone.setText("");
                etEmail.setText("");
                etPassword.setText("");

                if (Fname.length() < 2) {
                    Toast.makeText(Register.this, "שם פרטי קצר מדי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (Lname.length() < 2) {
                    Toast.makeText(Register.this, "שם משפחה קצר מדי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (Phone.length() < 9 || Phone.length() > 10) {
                    Toast.makeText(Register.this, "מספר הטלפון לא תקין", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (!Email.contains("@")) {
                    Toast.makeText(Register.this, "כתובת האימייל לא תקינה", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (Password.length() < 6) {
                    Toast.makeText(Register.this, "הסיסמה קצרה מדי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (Password.length() > 20) {
                    Toast.makeText(Register.this, "הסיסמה ארוכה מדי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }

                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    FirebaseUser fireuser = mAuth.getCurrentUser();
                                    User newUser = new User(fireuser.getUid(), Fname, Lname, Phone, Email, Password);
                                    myRef.child(fireuser.getUid()).setValue(newUser);

                                    Intent goLog = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(goLog);


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }


        }
    }



        }