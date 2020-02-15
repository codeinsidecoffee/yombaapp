package com.yomba.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class Language {
    private static String PREFS_LANGUAGE = "LANGUAGE";

    public static void setLanguage(Context context, String language) {


        // Update language
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }


}