package com.example.fp2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private Button btnRegister;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
//    private DatabaseReference database;

    public void onBackClick(View view) {
        onBackPressed();
    }

    //    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Jika user berhasil terautentikasi, lanjutkan dengan menyimpan data ke Firestore
                        saveToFirestore(etUsername.getText().toString().trim(),
                                etEmail.getText().toString().trim(),
                                etPassword.getText().toString().trim());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan saat autentikasi
                        Toast.makeText(getApplicationContext(), "Gagal mendaftar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveToFirestore(String nama, String email, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("nama", nama);
        user.put("email", email);
        user.put("password", password);

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        db.collection("users")
                .document(firebaseUser.getUid()) // Menggunakan UID dari pengguna sebagai ID dokumen
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Jika berhasil disimpan
                        Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan ke Firestore", Toast.LENGTH_SHORT).show();

                        // Redirect ke halaman login
                        Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                        startActivity(intent);
                        finish(); // Optional: Menutup aktivitas saat ini agar tidak kembali ke halaman registrasi saat tombol back ditekan
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan saat menyimpan
                        Toast.makeText(getApplicationContext(), "Gagal menyimpan data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "EROR" + e.getMessage());
                    }
                });
    }

}