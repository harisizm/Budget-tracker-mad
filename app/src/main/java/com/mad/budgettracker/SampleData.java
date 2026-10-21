package com.mad.budgettracker;

/**
 * Hard-coded sample transactions so the Lab 1 screens look real on the emulator.
 * The five arrays line up by index: row 0 is TITLES[0], CATEGORIES[0], and so on.
 * Lab 2 replaces this with a Transaction class and real rows from SQLite.
 */
public class SampleData {

    public static final String[] TITLES = {
            "Groceries", "Bus card", "Electricity bill", "Pizza night", "Medicine", "Headphones"
    };
    public static final String[] CATEGORIES = {
            "Food", "Transport", "Bills", "Food", "Health", "Shopping"
    };
    public static final String[] DATES = {
            "21/10/2026", "20/10/2026", "18/10/2026", "15/10/2026", "12/10/2026", "05/10/2026"
    };
    public static final String[] AMOUNTS = {
            "Rs 1,850", "Rs 600", "Rs 2,300", "Rs 1,200", "Rs 500", "Rs 1,000"
    };
    // true = expense, false = income. All sample rows are expenses, so the
    // Income filter on the Transactions screen shows the empty-state design.
    public static final boolean[] IS_EXPENSE = {
            true, true, true, true, true, true
    };
}
