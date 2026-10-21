package com.mad.budgettracker;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Add Transaction form. The Income/Expense toggle re-skins the form: category
 * list, labels, header and Save colour all change so the two modes read
 * differently. In Lab 1 Save only checks that an amount was typed and shows a
 * message; Lab 2 adds full validation and writes the row to SQLite.
 */
public class AddTransactionActivity extends AppCompatActivity {

    private View layoutHeader;
    private TextView labelCategory;
    private Spinner spinnerCategory;
    private TextInputLayout inputAmount;
    private TextInputLayout inputDate;
    private TextInputEditText editAmount;
    private MaterialButton buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        layoutHeader = findViewById(R.id.layoutHeader);
        labelCategory = findViewById(R.id.labelCategory);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        inputAmount = findViewById(R.id.inputAmount);
        inputDate = findViewById(R.id.inputDate);
        editAmount = findViewById(R.id.editAmount);
        buttonSave = findViewById(R.id.buttonSave);
        MaterialButton buttonCancel = findViewById(R.id.buttonCancel);
        MaterialButtonToggleGroup toggleType = findViewById(R.id.toggleType);

        // Runs every time a toggle button is checked or unchecked. We only react
        // to the "checked" event, otherwise the form would flip twice per tap.
        toggleType.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    showMode(checkedId == R.id.buttonExpense);
                }
            }
        });
        // The layout starts with Expense checked, so draw that mode first.
        showMode(true);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiny taste of validation so the error area is visible in Lab 1.
                String amount = editAmount.getText() == null ? "" : editAmount.getText().toString().trim();
                if (amount.isEmpty()) {
                    inputAmount.setError(getString(R.string.error_amount_empty));
                    return;
                }
                inputAmount.setError(null);
                // A Toast is a small message that fades out by itself.
                Toast.makeText(AddTransactionActivity.this,
                        R.string.toast_save_coming_soon, Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closing this screen returns to Home, which is still underneath it.
                finish();
            }
        });
    }

    /** Switches every type-specific part of the form between Expense and Income. */
    private void showMode(boolean isExpense) {
        int colour = ContextCompat.getColor(this, isExpense ? R.color.expense : R.color.income);
        int categoriesArray = isExpense ? R.array.categories_expense : R.array.categories_income;

        layoutHeader.setBackgroundColor(colour);
        // ColorStateList: a colour that can differ per state (pressed, disabled...);
        // valueOf makes one that is the same colour in every state.
        buttonSave.setBackgroundTintList(ColorStateList.valueOf(colour));
        labelCategory.setText(isExpense ? R.string.label_category_expense : R.string.label_category_income);
        inputDate.setHint(isExpense ? R.string.hint_date_expense : R.string.hint_date_income);

        // An adapter feeds items from a list into a view; here the string-array into the spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, categoriesArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }
}
