package rocks.byivo.processmanager.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import rocks.byivo.processmanager.builders.ProcessBuilder;
import rocks.byivo.processmanager.builders.ProcessStandBuilder;
import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.dto.ProcessStandDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.services.ProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-it.properties")
public class ProcessStandControllerIT {

    private static final String STAND_CONTENT = "STAND_CONTENT";

    @LocalServerPort
    int port;
    
    @Autowired
    private ProcessService processService;
    
    @Before
    public void setUp() throws Exception {
	RestAssured.port = port;
    }

    @Test
    public void 
    givenAExistingTask_whenUserWantsToStandAProcess_thenShouldCreateANewStand() {
	Process createdProcess = createNewProcess();
	ProcessStandDTO standToBeCreated = new ProcessStandBuilder()
		.withContent(STAND_CONTENT)
		.build()
		.toTransferObject();
	
	int generetedProcessId = createdProcess.getIdProcess().intValue();
	given()
		.basePath("process-manager/processes/" + generetedProcessId+ "/stands")
		.contentType(ContentType.JSON)
		.body(standToBeCreated)
	.when()
		.post()
	.then()
		.statusCode(201)
		.body("idProcessStand", any(Integer.class))
		.body("idProcess", equalTo(generetedProcessId))
		.body("content", equalTo(STAND_CONTENT))
		.body("createdAt", any(Date.class));
    }

    private Process createNewProcess() {
	ProcessDTO newProcessData = new ProcessBuilder()
		.withName("PROCESS NAME")
		.build()
		.toTransferObject();
	
	return processService.createProcessWith(newProcessData);
    }

}
