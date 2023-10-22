package cn.itmtx.ddd.ezlink.domain.domainobject;


public class SequenceAndCodeDO {

    /**
     * 10 进制
     */
    private String sequence;

    /**
     * 62 进制
     */
    private String compressionCode;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCompressionCode() {
        return compressionCode;
    }

    public void setCompressionCode(String compressionCode) {
        this.compressionCode = compressionCode;
    }
}
