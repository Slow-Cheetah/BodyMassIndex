package com.example.bodymassindex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IndexResult extends AppCompatActivity {

    ImageView massIV;
    TextView massresulTV;

    TextView recommendTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_index_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        massIV = findViewById(R.id.massIV);
        massresulTV = findViewById(R.id.massresulTV);
        recommendTV = findViewById(R.id.recommendTV);

        String mass = getIntent().getStringExtra("massKey").trim();
        String hight = getIntent().getStringExtra("heightKey").trim();
        Double result = calcBMI(mass, hight);
        String formatRes = String.format("%.2f", result);
        massresulTV.setText(formatRes);

        printResult(result);
    }

    public Double calcBMI(String mass, String hight) {
        Double wightInt = (double) 0;
        Double hightInt = (double) 0;
        Double bmi = (double) 0;
        if (mass.isEmpty()) {
            Toast.makeText(this, "Масса - ПУСТОЕ ПОЛЕ!", Toast.LENGTH_SHORT).show();
        } else {
            wightInt = Double.parseDouble(mass);
        }
        if (hight.isEmpty()) {
            Toast.makeText(this, "Рост - ПУСТОЕ ПОЛЕ!", Toast.LENGTH_SHORT).show();
        } else {
            hightInt = Double.parseDouble(hight);
        }
        bmi = (wightInt / (hightInt*hightInt))*10000;
        return bmi;
    }
    public void printResult(Double result) {
        TextList list = new TextList();
        if (result < 19) {
            recommendTV.setText("");
            for (String item : list.underWeight) recommendTV.append(item + "\n");
            massIV.setImageResource(R.drawable.body_mass_index_1);
        }
        if (19 <= result && result < 24) {
            recommendTV.setText("");
            for (String item : list.normalWeight) recommendTV.append(item + "\n");
            massIV.setImageResource(R.drawable.body_mass_index_2);
        }
        if (24 <= result && result < 30) {
            recommendTV.setText("");
            for (String item : list.overWeight) recommendTV.append(item + "\n");
            massIV.setImageResource(R.drawable.body_mass_index_3);
        }
        if (30 <= result && result < 40) {
            recommendTV.setText("");
            for (String item : list.obesity) recommendTV.append(item + "\n");
            massIV.setImageResource(R.drawable.body_mass_index_4);
        }
        if (40 <= result) {
            recommendTV.setText("");
            for (String item : list.extrimeObesity) recommendTV.append(item + "\n");
            massIV.setImageResource(R.drawable.body_mass_index_5);
        }
    }
}