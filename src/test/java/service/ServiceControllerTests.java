package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.controller.TikaServiceConfig;
import service.model.ServiceInformation;
import service.model.ServiceResponseContent;
import tika.legacy.LegacyPdfProcessorConfig;
import tika.model.TikaProcessingResult;
import tika.processor.CompositeTikaProcessorConfig;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = TikaServiceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TikaServiceConfig.class, LegacyPdfProcessorConfig.class, CompositeTikaProcessorConfig.class})
@TestPropertySource(properties = {"spring.config.location = classpath:tika/config/tika-processor-config.yaml,classpath:application.properties"})
public class ServiceControllerTests  {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceInformation serviceinfo;

    final private String INFO_ENDPOINT_URL = "/api/info";

    @Test
    public void testGetApplicationInfo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(INFO_ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // check response status
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        // parse content
        ObjectMapper mapper = new ObjectMapper();
        ServiceInformation response = mapper.readValue(result.getResponse().getContentAsString(),
                ServiceInformation.class);

        // check example content
        assertEquals(response.getServiceConfig().getAppVersion(), serviceinfo.getServiceConfig().getAppVersion());
    }
}
