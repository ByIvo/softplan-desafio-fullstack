package rocks.byivo.processmanager.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.After;
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
import rocks.byivo.processmanager.ContextPathConfig;
import rocks.byivo.processmanager.builders.ProcessBuilder;
import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.repositories.ProcessRepository;
import rocks.byivo.processmanager.services.ProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-it.properties")
public class ProcessControllerIT {

    private static final String BASE_PATH = ContextPathConfig.BASE_PATH + "/processes";

    private static final String PROCESS_NAME = "RANDOM_TASK_NAME";

    @LocalServerPort
    int port;
    
    @Autowired
    private ProcessRepository processRepository;
    
    @Autowired
    private ProcessService processService;
    
    @Before
    public void setUp() throws Exception {
	RestAssured.port = port;
    }
    
    @After
    public void tearDown() {
	processRepository.deleteAll();
    }

    @Test
    public void 
    when_user_wants_to_create_a_new_process__should_create_it_with_all_fields() {
	ProcessDTO newProcessFromClient = buildNewProcess();
	
	given()
		.basePath(BASE_PATH)
		.contentType(ContentType.JSON)
		.body(newProcessFromClient)
	.when()
		.post()
	.then()
		.statusCode(201)
		.body("idProcess", Matchers.any(Integer.class))
		.body("name", equalTo(PROCESS_NAME));
    }

    @Test
    public void 
    givenAnExistingProcess_whenFindById_thenReturnTheExistingProcess() throws Exception {
	Process createdProcess = processService.createProcessWith(buildNewProcess());
	Integer generatedIdProcess = createdProcess.getIdProcess().intValue();
	
	given()
		.basePath(BASE_PATH)
	.when()
		.get(generatedIdProcess + "")
	.then()
		.statusCode(200)
		.body("idProcess", equalTo(generatedIdProcess))
		.body("name", equalTo(PROCESS_NAME));
	
    }
    
    @Test
    public void 
    givenAnUnexistingProcess_whenFindById_thenReturnA404Status() throws Exception {
	Integer unexistingId = 999999;
	
	given()
		.basePath(BASE_PATH)
	.when()
		.get(unexistingId + "")
	.then()
		.statusCode(404);
	
    }

    private ProcessDTO buildNewProcess() {
	return new ProcessBuilder()
		.withName(PROCESS_NAME)
		.build()
		.toTransferObject();
    }
    
}
