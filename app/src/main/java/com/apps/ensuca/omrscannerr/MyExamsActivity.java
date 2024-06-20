package com.apps.ensuca.omrscannerr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.apps.ensuca.omrscanner.R;

public class MyExamsActivity extends AppCompatActivity {

    private LinearLayout examsLayout;
    private Set<Button> selectedButtons = new HashSet<>();
    private boolean isSelectionMode = false;
    private Button selectButton;
    private Button deleteSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exams);

        examsLayout = findViewById(R.id.exams_layout);

        loadExams();

        selectButton = new Button(this);
        selectButton.setText("Seç");
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSelectionMode();
            }
        });

        deleteSelectedButton = new Button(this);
        deleteSelectedButton.setText("Seçilenleri Sil");
        deleteSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedExams();
            }
        });

        examsLayout.addView(selectButton);
        examsLayout.addView(deleteSelectedButton);
    }

    private void toggleSelectionMode() {
        isSelectionMode = !isSelectionMode;
        for (int i = 0; i < examsLayout.getChildCount(); i++) {
            View child = examsLayout.getChildAt(i);
            if (child instanceof Button && child != selectButton && child != deleteSelectedButton) {
                final Button examButton = (Button) child;
                examButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isSelectionMode) {
                            toggleButtonSelection(examButton);
                        } else {
                            Intent intent = new Intent(MyExamsActivity.this, ScanActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    private void toggleButtonSelection(Button button) {
        if (selectedButtons.contains(button)) {
            selectedButtons.remove(button);
            button.setBackgroundColor(Color.TRANSPARENT);
        } else {
            selectedButtons.add(button);
            button.setBackgroundColor(Color.RED);
        }
    }

    private void deleteSelectedExams() {
        List<String> exams = new ArrayList<>();
        for (int i = 0; i < examsLayout.getChildCount(); i++) {
            View child = examsLayout.getChildAt(i);
            if (child instanceof Button && child != selectButton && child != deleteSelectedButton) {
                Button examButton = (Button) child;
                if (!selectedButtons.contains(examButton)) {
                    exams.add(examButton.getText().toString());
                }
            }
        }

        try (FileOutputStream fos = openFileOutput("exams.txt", MODE_PRIVATE)) {
            for (String exam : exams) {
                fos.write((exam + "\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        examsLayout.removeAllViews();
        loadExams();
        examsLayout.addView(selectButton);
        examsLayout.addView(deleteSelectedButton);
        selectedButtons.clear();
    }

    private void loadExams() {
        List<String> exams = new ArrayList<>();

        try (FileInputStream fis = openFileInput("exams.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                exams.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(exams, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String date1 = o1.substring(o1.lastIndexOf("oluşturulma tarihi: ") + 19);
                String date2 = o2.substring(o2.lastIndexOf("oluşturulma tarihi: ") + 19);
                return date2.compareTo(date1); // en son oluşturulan en başta
            }
        });

        for (String exam : exams) {
            Button examButton = new Button(this);
            examButton.setText(exam);
            examButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyExamsActivity.this, ScanActivity.class);
                    startActivity(intent);
                }
            });

            examsLayout.addView(examButton);
        }
    }
}
