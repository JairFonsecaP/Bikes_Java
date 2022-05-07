package com.isi.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyHelper
{
    private CurrencyHelper()
    { }

    public static NumberFormat getCurrencyFormat()
    {
        return getCurrencyFormat(Locale.getDefault());
    }

    public static NumberFormat getCurrencyFormat(Locale locale)
    {
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format;
    }
}
