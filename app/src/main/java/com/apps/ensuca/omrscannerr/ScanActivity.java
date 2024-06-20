package com.apps.ensuca.omrscannerr;

import android.app.Dialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.apps.ensuca.omrscanner.R;

public class ScanActivity extends AppCompatActivity {

    Button statisticsButton;

    Button scan,answer;
    boolean isCamera = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);

        scan = findViewById(R.id.scan);
        answer = findViewById(R.id.setAnswers);

        scan.setOnClickListener(v -> {
            Dialog dialog = new Dialog(ScanActivity.this);
            dialog.setContentView(R.layout.selection_dialog);
            dialog.findViewById(R.id.camera).setOnClickListener(view->{isCamera=true; dialog.cancel(); openActivity();});
            dialog.findViewById(R.id.gallery).setOnClickListener(view->{isCamera=false; dialog.cancel();openActivity();});
            dialog.show();

            statisticsButton = findViewById(R.id.statistics_button);
            statisticsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ScanActivity.this, StatisticsActivity.class));
                }
            });
//            openActivity();
        });


        answer.setOnClickListener(v -> startActivity(new Intent(ScanActivity.this, AnswersActivity.class)));
    }

    public void openActivity(){
        Intent intent = new Intent(ScanActivity.this,MainActivity.class);
        intent.putExtra("isCamera",isCamera);
        startActivity(intent);
    }

}