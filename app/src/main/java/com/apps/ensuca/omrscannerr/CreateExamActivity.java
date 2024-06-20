package com.apps.ensuca.omrscannerr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.apps.ensuca.omrscanner.R;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

public class CreateExamActivity extends AppCompatActivity {

    private EditText examNumberEditText;
    private EditText examNameEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        examNumberEditText = findViewById(R.id.exam_number);
        examNameEditText = findViewById(R.id.exam_name);
        saveButton = findViewById(R.id.save_exam);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExam();
                finish();
            }
        });
    }

    private void saveExam() {
        String examNumber = examNumberEditText.getText().toString();
        String examName = examNameEditText.getText().toString();
        String currentDate = new Date().toString();

        try {
            FileOutputStream fileOutputStream = openFileOutput("exams.txt", MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(examName + " Sınav Kodu: " + examNumber + " Date: " + currentDate + "\n");
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
