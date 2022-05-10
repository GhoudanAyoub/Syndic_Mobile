package com.syndicg5.networking.utils;

import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Syndic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kotlin.text.Regex;

public class Commun {
    public static String IP = "192.168.1.3:9090";

    public static String getClientNameInitials(String name) {
        String initials;
        Regex regex = new Regex("[-+.^:,]");
        String cleanName = regex.replace(name, "");
        if (!cleanName.isEmpty()) {
            initials = cleanName.length() < 2 ? cleanName.toUpperCase(Locale.ROOT) : cleanName.substring(0, 2).toUpperCase(Locale.ROOT);
        } else {
            initials = "AA";
        }

        return initials;
    }


    public static Syndic profile = new Syndic(1,"Ghoudan","Ayoub","Ayoubghoudanos@gmail.com","ayoub","LL1","kech","","0639603352");
}
