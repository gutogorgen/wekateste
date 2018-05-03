package augusto.devgridteste.wekatest.server.model;

import javax.persistence.*;

@Entity
public class ClientRequest {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String activePower;
    private String reactivePower;
    private String apparentPower;
    private String current;
    private String voltage;
    private Double transient1;
    private Double transient2;
    private Double transient3;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
