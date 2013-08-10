package com.example.Shop;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 09.08.13
 */
public class InputFilterMinMax implements InputFilter {
    private Integer min;
    private Integer max;

    public InputFilterMinMax(int min) {
        this.min = min;
    }

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());

            if (checkMin(input) && checkMax(input)) {
                return null;
            }

        } catch (NumberFormatException nfe) {
        }

        return "";
    }

    private boolean checkMin(int value) {
        return (min == null) || (value >= min);
    }

    private boolean checkMax(int value) {
        return (max == null) || (value <= max);
    }
}
