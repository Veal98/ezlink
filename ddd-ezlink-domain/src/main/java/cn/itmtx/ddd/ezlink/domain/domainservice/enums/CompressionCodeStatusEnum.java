package cn.itmtx.ddd.ezlink.domain.domainservice.enums;

/**
 * 压缩码状态
 */
public enum CompressionCodeStatusEnum {

    /**
     * 可用
     */
    AVAILABLE((byte) 1),

    /**
     * 已经使用
     */
    USED((byte) 2),

    /**
     * 非法
     */
    INVALID((byte) 3);

    private final Byte value;

    CompressionCodeStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
