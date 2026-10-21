package com.mad.budgettracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Dashboard screen. Shows the budget, spent and remaining cards, two shortcut
 * buttons, the three most recent transactions and the + button.
 * In Lab 1 every amount comes from strings.xml or SampleData; Lab 2 and 3 fill
 * them from SQLite and SharedPreferences.
 */
public class HomeActivity extends AppCompatActivity {

    // How many sample rows the dashboard previews; "View all" shows the rest.
    private static final int RECENT_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // findViewById links the Java variable to the view with that id in the XML.
        FloatingActionButton buttonAdd = findViewById(R.id.buttonAdd);
        MaterialButton buttonSetBudget = findViewById(R.id.buttonSetBudget);
        MaterialButton buttonViewAll = findViewById(R.id.buttonViewAll);
        LinearLayout layoutRecent = findViewById(R.id.layoutRecent);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // An Intent is a request to open another screen.
                Intent intent = new Intent(HomeActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        buttonSetBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SetBudgetActivity.class));
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TransactionListActivity.class));
            }
        });

        // Inflate = turn an XML layout file into real views. We do it once per
        // sample row and add the result to the card. Lab 3 lets RecyclerView do this.
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < RECENT_COUNT && i < SampleData.TITLES.length; i++) {
            View row = inflater.inflate(R.layout.item_transaction, layoutRecent, false);
            bindRow(row, i);
            layoutRecent.addView(row);
        }
    }

    /** Copies sample row number i into the TextViews of one inflated item_transaction. */
    private void bindRow(View row, int i) {
        TextView textInitial = row.findViewById(R.id.textInitial);
        TextView textTitle = row.findViewById(R.id.textRowTitle);
        TextView textSubtitle = row.findViewById(R.id.textRowSubtitle);
        TextView textAmount = row.findViewById(R.id.textRowAmount);

        textInitial.setText(SampleData.CATEGORIES[i].substring(0, 1));
        textTitle.setText(SampleData.TITLES[i]);
        textSubtitle.setText(SampleData.CATEGORIES[i] + " - " + SampleData.DATES[i]);
        // Expenses are red with a minus, income green with a plus.
        if (SampleData.IS_EXPENSE[i]) {
            textAmount.setText("- " + SampleData.AMOUNTS[i]);
            textAmount.setTextColor(ContextCompat.getColor(this, R.color.expense));
        } else {
            textAmount.setText("+ " + SampleData.AMOUNTS[i]);
            textAmount.setTextColor(ContextCompat.getColor(this, R.color.income));
        }
    }
}
