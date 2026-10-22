package com.mad.budgettracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.ChipGroup;

/**
 * Full list of transactions with an All / Income / Expense filter.
 * In Lab 1 the rows come from SampleData and the filter just hides rows;
 * Lab 3 replaces this with a RecyclerView reading SQLite.
 */
public class TransactionListActivity extends AppCompatActivity {

    private LinearLayout layoutRows;
    private View cardRows;
    private View layoutEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        layoutRows = findViewById(R.id.layoutRows);
        cardRows = findViewById(R.id.cardRows);
        layoutEmpty = findViewById(R.id.layoutEmpty);
        ChipGroup chipGroupFilter = findViewById(R.id.chipGroupFilter);

        // Build one row per sample transaction, exactly like HomeActivity does.
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < SampleData.TITLES.length; i++) {
            View row = inflater.inflate(R.layout.item_transaction, layoutRows, false);
            bindRow(row, i);
            layoutRows.addView(row);
        }

        // Called whenever a different chip becomes checked.
        chipGroupFilter.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, java.util.List<Integer> checkedIds) {
                applyFilter(group.getCheckedChipId());
            }
        });
        applyFilter(chipGroupFilter.getCheckedChipId());
    }

    /** Shows only the rows that match the checked chip; shows the empty state if none do. */
    private void applyFilter(int checkedChipId) {
        int visibleCount = 0;
        for (int i = 0; i < layoutRows.getChildCount(); i++) {
            boolean show;
            if (checkedChipId == R.id.chipIncome) {
                show = !SampleData.IS_EXPENSE[i];
            } else if (checkedChipId == R.id.chipExpense) {
                show = SampleData.IS_EXPENSE[i];
            } else {
                show = true;
            }
            // GONE removes the view from the layout; VISIBLE puts it back.
            layoutRows.getChildAt(i).setVisibility(show ? View.VISIBLE : View.GONE);
            if (show) {
                visibleCount++;
            }
        }
        cardRows.setVisibility(visibleCount > 0 ? View.VISIBLE : View.GONE);
        layoutEmpty.setVisibility(visibleCount > 0 ? View.GONE : View.VISIBLE);
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
        if (SampleData.IS_EXPENSE[i]) {
            textAmount.setText("- " + SampleData.AMOUNTS[i]);
            textAmount.setTextColor(ContextCompat.getColor(this, R.color.expense));
        } else {
            textAmount.setText("+ " + SampleData.AMOUNTS[i]);
            textAmount.setTextColor(ContextCompat.getColor(this, R.color.income));
        }
    }
}
