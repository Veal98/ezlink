package cn.itmtx.ddd.ezlink.component.keygen;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public enum ConversionUtils {

    /**
     * 单例
     */
    X;

    private static final String CHARS = "oNWxUYwrXdCOIj4ck6M8RbiQa3H91pSmZTAh70zquLnKvt2VyEGlBsPJgDe5Ff";
    private static final int SCALE = 62;


    /**
     * 10 进制转 62 进制
     *
     * @param num num
     * @param length 62 进制压缩码长度
     * @return String
     */
    public String encode62(long num, int length) {
        StringBuilder builder = new StringBuilder();
        int remainder;
        while (num > SCALE - 1) {
            remainder = Long.valueOf(num % SCALE).intValue();
            builder.append(CHARS.charAt(remainder));
            num = num / SCALE;
        }
        builder.append(CHARS.charAt(Long.valueOf(num).intValue()));
        String value = builder.reverse().toString();
        // 从左往右截取 compressionCodeLength 个字符，长度不够的话填充 0
        return StringUtils.leftPad(value, length, '0');
    }
}
