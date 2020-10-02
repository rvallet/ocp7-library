package com.library.mslibrary.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomTools {

    public static int randomNum (int min, int max) throws NoSuchAlgorithmException {
        SecureRandom ran = new SecureRandom();
        return ran.nextInt(max-min+1)+min;
    }

    public static boolean randomBoolean() throws NoSuchAlgorithmException {
        SecureRandom ran = SecureRandom.getInstanceStrong();
        return ran.nextBoolean();
    }
}
