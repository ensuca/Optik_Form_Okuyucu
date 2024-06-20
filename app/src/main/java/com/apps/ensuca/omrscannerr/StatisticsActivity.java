package com.apps.ensuca.omrscannerr;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.FileOutputStream;
import com.apps.ensuca.omrscanner.R;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private TextView statisticsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statisticsTextView = findViewById(R.id.statisticsTextView);
        Button getExcelButton = findViewById(R.id.get_excel);

        loadStudentGrades();

        getExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataAsExcel();
            }
        });
    }

    private void saveDataAsExcel() {
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "OMR");
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, "Statistics.xls");

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Statistics", 0);

            // Create header row
            sheet.addCell(new Label(0, 0, "Öğrenci NO"));
            sheet.addCell(new Label(1, 0, "Puan"));

            // Add data rows from the statisticsTextView
            String[] lines = statisticsTextView.getText().toString().split("\n");
            for (int i = 0; i < lines.length; i++) {
                String[] parts = lines[i].split(": ");
                if (parts.length == 2) {
                    sheet.addCell(new Label(0, i + 1, parts[0].trim()));
                    sheet.addCell(new Label(1, i + 1, parts[1].trim()));
                }
            }

            workbook.write();
            workbook.close();
            Toast.makeText(this, "Exel dosyası başarı ile kaydedildi.", Toast.LENGTH_SHORT).show();
        } catch (IOException | jxl.write.WriteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exel dosyasını kayıt ederken bir hata meydana geldi.", Toast.LENGTH_SHORT).show();
        }
    }


    public void loadStudentGrades() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/OMR/student_gradeses.txt");
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line).append('\n');
                }
                br.close();
                if (statisticsTextView != null) {
                    statisticsTextView.setText(text.toString());
                    Log.d("StatisticsActivity", "Loaded data: " + text.toString());
                } else {
                    Log.e("StatisticsActivity", "statisticsTextView is null");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading statistics", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (statisticsTextView != null) {
                statisticsTextView.setText("No data found.");
            } else {
                Log.e("StatisticsActivity", "statisticsTextView is null");
            }
            Log.d("StatisticsActivity", "File not found.");
        }
    }
}

