package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    BarChart barChart;
    CardView totaluser,totalpost,topSubmitters,topVoters;
    int totalpostsize=0;
    TextView textUser;
    int totalusers;

    Toolbar toolbar;


    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        barChart = findViewById(R.id.barchartid);
        textUser=findViewById(R.id.textTotaluser);
        toolbar=findViewById(R.id.custom_toolbar);



        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(100);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        gettingData();
        textUser.setText(String.valueOf(totalusers));
        Toast.makeText(AdminPanel.this,String.valueOf(totalusers),Toast.LENGTH_LONG).show();





        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1,totalusers));
        barEntries.add(new BarEntry(2, totalpostsize));
        barEntries.add(new BarEntry(3, 10f));
        barEntries.add(new BarEntry(4, 40f));
        barEntries.add(new BarEntry(5, 40f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Summury");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(.7f);

        barChart.setData(barData);

        totaluser=findViewById(R.id.totaluserid);
        totalpost=findViewById(R.id.totalpost);
        topSubmitters=findViewById(R.id.topSubmitters);
        topVoters=findViewById(R.id.topVoters);

        totaluser.setOnClickListener(this);
        totalpost.setOnClickListener(this);
        topSubmitters.setOnClickListener(this);
        topVoters.setOnClickListener(this);

        setSupportActionBar(toolbar);



        getSupportActionBar().setTitle("Dashboard");



    }

    private void gettingData() {

        db.collection("Ideas")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult())
                    {

                    }
                    //Log.d("Totalp",String.valueOf(totalpostsize));
                }

            }
        });

        db.collection("userinfo")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.totaluserid:
                break;

            case R.id.totalpost:
                break;
              case R.id.topSubmitters:
                break;
              case R.id.topVoters:
                break;

        }

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logoutid:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminPanel.this,MainActivity.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
