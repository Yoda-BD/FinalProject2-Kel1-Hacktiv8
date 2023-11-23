//package com.example.fp2;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//public class StaffActivity extends AppCompatActivity {
//
//    private CardView cardView;
//    private TextView noDataText;
//    private Button addButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_staff_admin);
//
//        // Inisialisasi elemen UI
//        cardView = findViewById(R.id.card_view);
//        noDataText = findViewById(R.id.no_data_text);
//        addButton = findViewById(R.id.add_button);
//
//        // Cek apakah data tersedia dari Firebase atau tidak
//        boolean isDataAvailable = // Lakukan pengecekan apakah data tersedia dari Firebase atau tidak
//
//        if (isDataAvailable) {
//            // Data tersedia, tampilkan CardView dengan informasi dari Firebase
//            cardView.setVisibility(View.VISIBLE);
//            noDataText.setVisibility(View.GONE);
//
//            // Lakukan pengisian data pada CardView sesuai dengan informasi dari Firebase
//            // Misalnya, isi TextViews untuk Nama, Email, Password, dll.
//        } else {
//            // Data tidak tersedia, tampilkan pesan "No Data"
//            cardView.setVisibility(View.GONE);
//            noDataText.setVisibility(View.VISIBLE);
//        }
//
//        // Aksi ketika tombol "Add" ditekan
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Implementasikan aksi ketika tombol "Add" ditekan
//                // Misalnya, buka aktivitas untuk menambahkan data baru
//            }
//        });
//    }
//}
