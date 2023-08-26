package cn.itmtx.ddd.ezlink.domain.transform;

/**
 * 压缩码状态
 */
public enum CompressionCodeStatus {

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

    CompressionCodeStatus(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
