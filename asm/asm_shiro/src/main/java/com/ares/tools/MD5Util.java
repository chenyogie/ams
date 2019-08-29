package com.ares.tools;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
    private static final String SALT = "zclyx";
    private static final Integer HASHITERATIONS = 5;

    public static String createEncryptPwdWithMD5(String password){
        SimpleHash encryPW = new SimpleHash("MD5",password,SALT,HASHITERATIONS);
        return encryPW.toString();


    }

}
