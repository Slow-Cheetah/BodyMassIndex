package com.example.bodymassindex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText massET;

    EditText heightET;

    Button calcMassBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        massET = findViewById(R.id.massET);
        heightET = findViewById(R.id.heightET);
        calcMassBTN = findViewById(R.id.calcMassBTN);

//        calcMassBTN.setOnClickListener(v -> {
//            Intent intent = new Intent(this, IndexResult.class);
//            startForResult.launch(intent);
//        });


        calcMassBTN.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexResult.class);
            intent.putExtra("massKey", massET.getText().toString());
            intent.putExtra("heightKey", heightET.getText().toString());
            startActivity(intent);
        });
    }
//    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult o) {
//
//                }
//            }
//    );
}