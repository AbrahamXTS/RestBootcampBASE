package com.bancobase.bootcamp.utils;

import java.util.*;

public class Utils {

    private Utils() throws InstantiationException {
        throw new InstantiationException("The utility classes can not be instantiated");
    }

    private static final Random random = new Random();

    public static String generateAccountNumber() {
        List<Character> accountNumberChars = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            char randomChar = (char) (random.nextInt(10) + '0');
            accountNumberChars.add(randomChar);
        }

        StringBuilder accountNumber = new StringBuilder()
                .append("41523136");

        for (Character c : accountNumberChars) {
            accountNumber.append(c);
        }

        return accountNumber.toString();
    }
}
