package rocks.byivo.processmanager.services.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.processmanager.builders.ProcessBuilder;
import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.repositories.ProcessRepository;
import rocks.byivo.processmanager.services.ProcessService;

@RunWith(SpringRunner.class)
public class ProcessServiceImplTest {
    
    private static final String PROCESS_NAME = "PROCESS_NAME";

    @Mock
    private ProcessRepository processRepository;

    private ProcessService processService;
    
    @Before
    public void setUp() throws Exception {
	processService = new ProcessServiceImpl(processRepository);
    }

    @Test
    public void whenCreatingANewProcess_thenCallSaveInRepositoryWithSameProcessName() {
	ProcessDTO newProcessData = buildNewProcessData();
	
	processService.createProcessWith(newProcessData);
	
	Process savedProcess = captureSavedTaskFromRepository();
	
	assertThat(savedProcess.getName(), is(PROCESS_NAME));
    }

    @Test
    public void whenCreatingANewProcess_thenShouldFillTheCreatedAndUpdatedAtDate() throws Exception {
	ProcessDTO newProcessData = buildNewProcessData();
	
	Date saveStartedMoment = new Date();
	processService.createProcessWith(newProcessData);
	Date saveFinishedMoment = new Date();
	Process savedProcess = captureSavedTaskFromRepository();
	
	assertThat(savedProcess.getCreatedAt(), between(saveStartedMoment, saveFinishedMoment));
	assertThat(savedProcess.getUpdatedAt(), between(saveStartedMoment, saveFinishedMoment));
    }
    
    private ProcessDTO buildNewProcessData() {
	return new ProcessBuilder()
		.withName(PROCESS_NAME)
		.build()
		.toTransferObject();
    }
    
    private Process captureSavedTaskFromRepository() {
	ArgumentCaptor<Process> processCaptor = ArgumentCaptor.forClass(Process.class);
	verify(processRepository).save(processCaptor.capture());
	return processCaptor.getValue();
    }
    

    private Matcher<Date> between(Date startDate, Date endindDate) {

	return new Matcher<Date>() {

	    @Override
	    public void describeTo(Description description) {
	    }

	    @Override
	    public boolean matches(Object item) {
		Date actual = (Date) item;
		return actual.compareTo(startDate) >= 0 && actual.compareTo(endindDate) <= 0;
	    }

	    @Override
	    public void describeMismatch(Object item, Description mismatchDescription) {
	    }

	    @Override
	    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
	    }

	};
    }
    
}
