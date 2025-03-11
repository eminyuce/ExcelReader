package com.excel.reader.util;

public class GeneralHelper {

    public static String replaceTurkishChars(String input) {
        if (input == null) return null;
        return input.replace("İ", "I").replace("ı", "i")
                .replace("Ğ", "G").replace("ğ", "g")
                .replace("Ş", "S").replace("ş", "s")
                .replace("Ç", "C").replace("ç", "c")
                .replace("Ö", "O").replace("ö", "o")
                .replace("Ü", "U").replace("ü", "u");
    }

}
