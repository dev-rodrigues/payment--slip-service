package br.com.devrodrigues.slipservice.datasources.http.entity;

public class SlipPaymentResponseData {
    private String code;
    private String status;

    public SlipPaymentResponseData() {
    }

    public SlipPaymentResponseData(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
