package cn.itmtx.ddd.ezlink.domain.domainservice.util;

import org.apache.commons.lang3.StringUtils;

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
        if (num < 0) {
            throw new IllegalArgumentException("This is an invalid 10 hex num:" + num);
        }

        StringBuilder builder = new StringBuilder();
        int remainder;
        while (Math.abs(num) > SCALE - 1) {
            // 从最后一位开始进制转换，取转换后的值，最后反转字符串
            remainder = Long.valueOf(num % SCALE).intValue();
            builder.append(CHARS.charAt(remainder));
            num = num / SCALE;
        }
        // 获取最高位
        builder.append(CHARS.charAt(Long.valueOf(num).intValue()));
        String value = builder.reverse().toString();
        // 从左往右截取 compressionCodeLength 个字符，长度不够的话填充 0
        return StringUtils.leftPad(value, length, '0');
    }
}
