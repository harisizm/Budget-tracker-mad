package com.mad.budgettracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

/**
 * Set Budget screen. In Lab 1 it only shows the layout; Save shows a message.
 * Lab 3 stores the number in SharedPreferences and Home reads it back.
 */
public class SetBudgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        MaterialButton buttonSave = findViewById(R.id.buttonSave);
        MaterialButton buttonCancel = findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetBudgetActivity.this,
                        R.string.toast_budget_coming_soon, Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
