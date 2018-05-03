package augusto.devgridteste.wekatest.server;

import augusto.devgridteste.wekatest.server.model.ClientRequest;
import augusto.devgridteste.wekatest.server.model.OutputRequest;
import augusto.devgridteste.wekatest.server.repository.ClientJpaRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WekaController {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    private Integer numOfClusters;

    public WekaController() {
    }

    Gson gson = new Gson();

    /**
     * Set the value to number of the clusters
     * @param numOfClusters (value)
     */
    @RequestMapping(value = "/num_clusters/{numOfClusters}", method = RequestMethod.GET)
    public void setNumOfClusters(@PathVariable("numOfClusters") Integer numOfClusters) {
        this.numOfClusters = numOfClusters;
    }

    /**
     * Seach all of events stored in database
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<ClientRequest> findAll() {
        return clientJpaRepository.findAll();
    }

    /**
     * Save new event, if have 1000 or more events on database execute de SimpleKmeans method
     * @param input content sended in post request
     * @return if have 1000 or more events in database, this method returns a Json
     * with number of clusters an the number of events per cluster
     */
    @RequestMapping(value = "/add_sensor_record", method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public String saveAndProcess(@RequestBody ClientRequest input) {

        clientJpaRepository.save(input);

        if (clientJpaRepository.count() >= 1000) {
            SimpleKMeans model = simpleEmMethod();

            OutputRequest outputRequest = new OutputRequest();

            for (int i = 0; i <= model.getNumClusters() - 1; i++) {
                outputRequest.getEventsInCluster().put(i, model.getClusterSizes()[i]);
            }

            if (outputRequest != null) {
                return gson.toJson(outputRequest);
            }
        }
        return "added";
    }

    /**
     * The method execute the SimpleKMeans algorithm of Weka API
     * @return
     */
    private SimpleKMeans simpleEmMethod() {
        //Simple EM (expectation maximisation)
        SimpleKMeans model = new SimpleKMeans();

        try {

            InstanceQuery query = new InstanceQuery();
            query.setUsername("root");
            query.setDatabaseURL("jdbc:mysql://localhost:3306/wekadb");

            query.setQuery("SELECT " +
                    "active_power, apparent_power, reactive_power, current, voltage, transient1, transient2, transient3" +
                    " FROM client_request");

            Instances data = query.retrieveInstances();

            System.out.println(data);

            if (numOfClusters != null) {
                model.setNumClusters(numOfClusters);
            }

            model.buildClusterer(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
