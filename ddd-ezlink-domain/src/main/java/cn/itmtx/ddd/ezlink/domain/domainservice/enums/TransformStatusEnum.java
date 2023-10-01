package cn.itmtx.ddd.ezlink.domain.domainservice.enums;

/**
 * 短链转换状态
 */
public enum TransformStatusEnum {
    /**
     * 转换成功
     */
    TRANSFORM_SUCCESS((byte) 1),

    /**
     * 转换失败
     */
    TRANSFORM_FAIL((byte) 2),

    /**
     * 重定向成功
     */
    REDIRECTION_SUCCESS((byte) 3),

    /**
     * 重定向失败
     */
    REDIRECTION_FAIL((byte) 4);


    private final Byte value;

    TransformStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
