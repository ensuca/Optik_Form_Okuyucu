package com.apps.ensuca.omrscannerr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.apps.ensuca.omrscannerr.MainActivity.file;

import com.apps.ensuca.omrscanner.R;

public class   Results extends AppCompatActivity {

    TextView scoreView;
    EditText studentNumber;
    Button saveButton;
    Button checkAnswersButton;

    String grade;

    TextView results,score;
    Button check;
    int MY_STORAGE_PERMISSION_CODE  = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Scanned Results");

        results = findViewById(R.id.answers);
        results.setText(readFile(file));
        results.setVisibility(View.VISIBLE);
        score = findViewById(R.id.score);
        score.setVisibility(View.GONE);

        check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_PERMISSION_CODE);
                }

                final File path =
                        Environment.getExternalStoragePublicDirectory
                                (
                                        Environment.DIRECTORY_DOCUMENTS + "/OMR/"
                                );

                File f = new File(path, "key.txt");
                String[] key = readFile(f).split("\n");
                String[] answers = readFile(file).split("\n");
                double maxscore = answers.length;
                if(maxscore==0 || key.length==0){
                    Toast.makeText(getApplicationContext(),"Cevap Anahtarı bulunamadı.\nLütfen Önce Bir Cevap Anahtarı oluşturun.",Toast.LENGTH_SHORT).show();
                }
                double s = 0;
                try {
                    for (int i = 0; i < maxscore; i++) {
                        int ind = key[i].indexOf('.');
                        String actual = key[i].substring(ind + 1).trim();
                        ind = answers[i].indexOf('.');
                        String found = answers[i].substring(ind + 1).trim();
                        if (actual.equalsIgnoreCase(found))
                            ++s;
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Cevap Anahtarı bulunamadı.\nLütfen Önce Bir Cevap Anahtarı oluşturun.",Toast.LENGTH_SHORT).show();
                    Log.d("ERROR",e.getMessage().toString());
                    finishAffinity();
                    startActivity(new Intent(Results.this, ScanActivity.class));
                }
                score.setVisibility(View.VISIBLE);
                score.setText("Alınan Puan: "+s/maxscore*100+"%");
                check.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Doğru Sayısı "+s+ "Yanlış Sayısı: "+(20-s)+"/",Toast.LENGTH_SHORT).show();
                grade = String.valueOf((s/maxscore*100));

            }
        });
        studentNumber = findViewById(R.id.student_number);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentNum = studentNumber.getText().toString().trim();
                if (studentNum.isEmpty()) {
                    Toast.makeText(Results.this, "Öğrenci Numarası Girmelisiniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Assuming the grade is calculated and stored in a variable 'grade'
                // Placeholder
                saveStudentGrades(studentNum, grade);
            }
        });
    }

    private void saveStudentGrades(String studentNumber, String grade) {
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "OMR");
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, "student_gradeses.txt");

        try (FileOutputStream fos = new FileOutputStream(file, true)) { // 'true' to append to file
            String data = studentNumber + ": " + grade + "\n";
            fos.write(data.getBytes());
            fos.flush();
            Toast.makeText(this, "Öğrenci verileri başarı ile kayıt edildi.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Öğrenci verilerini kayıt ederken bir hata meydana geldi.", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine())!=null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Cevap Anahtarı bulunamadı.\nLütfen Önce Bir Cevap Anahtarı oluşturun.",Toast.LENGTH_SHORT).show();
            Log.d("ERROR",e.getMessage().toString());
            finishAffinity();
            startActivity(new Intent(Results.this, ScanActivity.class));
        }
        String result = text.toString();
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_STORAGE_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                check.callOnClick();
            } else {
                Toast.makeText(this, "Depolama İzni Reddedildi", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
