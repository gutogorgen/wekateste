package augusto.devgridteste.wekatest.client.model;


public class ClientRequest {

    private String activePower;
    private String reactivePower;
    private String apparentPower;
    private String current;
    private String voltage;
    private Double transient1;
    private Double transient2;
    private Double transient3;

    public ClientRequest(){}

    public ClientRequest(String activePower, String reactivePower, String apparentPower, String current, String voltage, Double transient1, Double transient2, Double transient3) {
        this.activePower = activePower;
        this.reactivePower = reactivePower;
        this.apparentPower = apparentPower;
        this.current = current;
        this.voltage = voltage;
        this.transient1 = transient1;
        this.transient2 = transient2;
        this.transient3 = transient3;
    }

    public String getActivePower() {
        return activePower;
    }

    public void setActivePower(String activePower) {
        this.activePower = activePower;
    }

    public String getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(String reactivePower) {
        this.reactivePower = reactivePower;
    }

    public String getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(String apparentPower) {
        this.apparentPower = apparentPower;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public Double getTransient1() {
        return transient1;
    }

    public void setTransient1(Double transient1) {
        this.transient1 = transient1;
    }

    public Double getTransient2() {
        return transient2;
    }

    public void setTransient2(Double transient2) {
        this.transient2 = transient2;
    }

    public Double getTransient3() {
        return transient3;
    }

    public void setTransient3(Double transient3) {
        this.transient3 = transient3;
    }
}
