package com.ares.shiro;

import com.ares.tools.MD5Util;
import org.junit.Test;

public class tests  {
    @Test
    public void testWith() throws Exception{
        String encryptPwdWithMD5 = MD5Util.createEncryptPwdWithMD5("1");
        System.out.println(encryptPwdWithMD5);
    }
}
