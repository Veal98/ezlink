package cn.itmtx.ddd.ezlink.domain.domainobject;

public class CompressionCodeDO {

    private Long id;

    private String compressionCode;

    private Byte codeStatus;

    private String sequenceValue;

    private String strategy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Byte codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getCompressionCode() {
        return compressionCode;
    }

    public void setCompressionCode(String compressionCode) {
        this.compressionCode = compressionCode;
    }

    public String getSequenceValue() {
        return sequenceValue;
    }

    public void setSequenceValue(String sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
