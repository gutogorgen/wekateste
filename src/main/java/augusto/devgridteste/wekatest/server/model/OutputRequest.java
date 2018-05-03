package augusto.devgridteste.wekatest.server.model;

import java.util.HashMap;
import java.util.Map;

public class OutputRequest {

    private Double powerAverage;
    private Map<Integer, Double> eventsInCluster;

    public OutputRequest() {
        eventsInCluster = new HashMap<>();
    }

    public OutputRequest(Double powerAverage, Map<Integer, Double> eventsInCluster) {
        this.powerAverage = powerAverage;
        this.eventsInCluster = eventsInCluster;
    }

    public Double getPowerAverage() {
        return powerAverage;
    }

    public void setPowerAverage(Double powerAverage) {
        this.powerAverage = powerAverage;
    }

    public Map<Integer, Double> getEventsInCluster() {
        return eventsInCluster;
    }

    public void setEventsInCluster(Map<Integer, Double> eventsInCluster) {
        this.eventsInCluster = eventsInCluster;
    }
}
