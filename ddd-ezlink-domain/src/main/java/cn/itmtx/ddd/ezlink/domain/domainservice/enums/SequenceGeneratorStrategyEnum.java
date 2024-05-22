package cn.itmtx.ddd.ezlink.domain.domainservice.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 10进制压缩码生成策略枚举类
 */
public enum SequenceGeneratorStrategyEnum {

    /**
     * Hash
     */
    HASH("hash"),

    /**
     * Id 生成器
     */
    ID("id");

    private String strategyName;

    public static SequenceGeneratorStrategyEnum findBy(String strategyName) {
        if (StringUtils.isEmpty(strategyName)) {
            return null;
        }

        return Arrays.stream(SequenceGeneratorStrategyEnum.values())
                .filter(sequenceGeneratorStrategyEnum -> sequenceGeneratorStrategyEnum.getStrategyName().equalsIgnoreCase(strategyName))
                .findFirst()
                .orElse(null);
    }

    SequenceGeneratorStrategyEnum(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }
}
