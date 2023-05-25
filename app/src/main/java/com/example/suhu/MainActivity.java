package com.example.suhu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView mSuhuTextView, mKadarAlkoholTextView, mRealtimeConditionTextView, mLastUpdateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSuhuTextView = findViewById(R.id.suhu_text_view);
        mKadarAlkoholTextView = findViewById(R.id.kadar_alkohol_text_view);
        mRealtimeConditionTextView = findViewById(R.id.realtime_condition_text_view);
        mLastUpdateTextView = findViewById(R.id.last_update_text_view);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("histori");
        Query query = databaseReference.orderByChild("waktu").limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String waktu = snapshot.child("waktu").getValue(String.class);
                    String[] waktuSplit = waktu.split(" ");
                    String tanggalJam = waktuSplit[1] + " / " + waktuSplit[2];

                    double suhu = snapshot.child("suhu").getValue(Double.class);
                    double alkohol = snapshot.child("alkohol").getValue(Double.class);
                    String status = snapshot.child("status").getValue(String.class);

                    mSuhuTextView.setText(String.format("%.1f °C", suhu));
                    mKadarAlkoholTextView.setText(String.format("%.1f %%", alkohol));
                    mRealtimeConditionTextView.setText(status);
                    mLastUpdateTextView.setText(tanggalJam);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else
                if (id == R.id.menu_list) {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu_keluar) {
                    finish();
                    return true;
                }
                return false;
            }
        });
        popup.inflate(R.menu.menu_main);
        popup.show();
    }
}