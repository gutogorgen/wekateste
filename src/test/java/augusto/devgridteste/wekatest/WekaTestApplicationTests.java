package augusto.devgridteste.wekatest;

import augusto.devgridteste.wekatest.server.model.ClientRequest;
import augusto.devgridteste.wekatest.server.repository.ClientJpaRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WekaTestApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientJpaRepository clientJpaRepository;

    @Mock
    ClientRequest clientRequest;

    @Before
    public void inicializa() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() throws Exception {

        testFindAll();

        testAddSensorRecord();
    }

    public void testFindAll() throws Exception {
        when(clientJpaRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/all/")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        System.out.println(mvcResult.getResponse());

        verify(clientJpaRepository).findAll();
    }

    public void testAddSensorRecord() throws Exception {

        clientRequest = new ClientRequest();
        Gson gson = new Gson();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/add_sensor_record/")
                .content(gson.toJson(clientRequest)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
        assertEquals(mvcResult.getResponse().getContentAsString(), "added");

    }

}
