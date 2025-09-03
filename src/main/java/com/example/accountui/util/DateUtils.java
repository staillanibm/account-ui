package com.example.accountui.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("Europe/Paris");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /**
     * Convertit une LocalDateTime (supposée en UTC) vers l'heure CET/CEST et la formate
     */
    public static String formatToCET(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        
        // Assume que la LocalDateTime est en UTC
        ZonedDateTime utcDateTime = dateTime.atZone(UTC);
        // Convertit vers CET/CEST
        ZonedDateTime cetDateTime = utcDateTime.withZoneSameInstant(CET);
        
        return cetDateTime.format(FORMATTER);
    }
}