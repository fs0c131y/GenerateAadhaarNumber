package com.fs0c131y.generateaadhaarnumber.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fs0c131y.generateaadhaarnumber.R;

import java.util.Random;

import static com.fs0c131y.generateaadhaarnumber.util.Verhoeff.generateVerhoeff;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView aadhaarNumber = findViewById(R.id.generated_aadhaar_number);
        aadhaarNumber.setText(generateRandomAadhaarNumber());

        Button generateButton = findViewById(R.id.generate_button);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aadhaarNumber.setText(generateRandomAadhaarNumber());
            }
        });

        TextView labelMoreInfo = findViewById(R.id.label_more_info);
        labelMoreInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private String generateRandomAadhaarNumber() {
        StringBuilder randomAadhaarNumber = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            Random rand = new Random();
            int value = rand.nextInt(10);
            randomAadhaarNumber.append(Integer.toString(value));
        }

        String checksum = generateVerhoeff(randomAadhaarNumber.toString());

        return randomAadhaarNumber + checksum;
    }
}
