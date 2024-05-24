package cn.itmtx.ddd.ezlink.domain.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String createRandomStr(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
