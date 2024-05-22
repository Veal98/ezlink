package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

/**
 * 10进制压缩码生成
 */
public interface DecimalSequenceGenerator {

    /**
     * 策略名称
     * @return
     */
    String strategyName();

    /**
     * 生成 10 进制压缩码
     *
     * @param longUrl
     * @return
     */
    long generateDecimalSequence(String longUrl);

    /**
     * 处理冲突，返回不冲突的 10 进制压缩码
     * @param longUrl
     * @param compressionCodeLength
     * @return
     */
    long fixConflict(String longUrl, Integer compressionCodeLength);
}
