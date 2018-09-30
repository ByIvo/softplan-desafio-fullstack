package rocks.byivo.processmanager.services.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.processmanager.utils.CustomMatchers.between;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.processmanager.builders.ProcessStandBuilder;
import rocks.byivo.processmanager.dto.ProcessStandDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.model.ProcessStand;
import rocks.byivo.processmanager.repositories.ProcessStandRepository;
import rocks.byivo.processmanager.services.ProcessStandService;

@RunWith(SpringRunner.class)
public class ProcessStandServiceImplTest {
    
    private static final String STAND_CONTENT = "STAND_CONTENT";

    @Mock
    private ProcessStandRepository processStandRepository;

    private ProcessStandService processStandService;
    
    @Before
    public void setUp() throws Exception {
	processStandService = new ProcessStandServiceImpl(processStandRepository);
    }

    @Test
    public void whenStandingForAProcess_thenCreateANewOneWithSameContent() {
	Process process = mock(Process.class);
	
	ProcessStandDTO standToBeCreated = createNewStand();
	
	processStandService.standForProcess(process, standToBeCreated);
	
	ProcessStand generatedStandToSave = captureSavedFulfilledStand();
	
	assertThat(generatedStandToSave.getContent(), is(STAND_CONTENT));
    }
    
    @Test
    public void whenStandingForAProcess_thenShouldFillTheProcess() throws Exception {
	Process process = mock(Process.class);
	
	ProcessStandDTO standToBeCreated = createNewStand();
	
	processStandService.standForProcess(process, standToBeCreated);
	
	ProcessStand generatedStandToSave = captureSavedFulfilledStand();
	
	assertThat(generatedStandToSave.getProcess(), is(process));
    }
    
    @Test
    public void whenStandingForAProcess_thenShouldFillTheCreatedAndUpdatedAtDates() throws Exception {
	Process process = mock(Process.class);
	
	ProcessStandDTO standToBeCreated = createNewStand();
	
	Date standProcessStarting = new Date();
	processStandService.standForProcess(process, standToBeCreated);
	Date standProcessEnding = new Date();
	
	ProcessStand generatedStandToSave = captureSavedFulfilledStand();
	
	assertThat(generatedStandToSave.getCreatedAt(), between(standProcessStarting, standProcessEnding));
	assertThat(generatedStandToSave.getUpdatedAt(), between(standProcessStarting, standProcessEnding));
    }
    
    @Test
    public void whenStandingForAProcess_thenShouldReturnTheSavedStand() throws Exception {
	Process process = mock(Process.class);
	
	ProcessStand expectedCreatedStand = mock(ProcessStand.class);
	when(processStandRepository.save(Mockito.any()))
		.thenReturn(expectedCreatedStand);
	
	ProcessStand createdStand = processStandService.standForProcess(process, createNewStand());
	
	assertThat(createdStand, is(expectedCreatedStand));
    }

    private ProcessStandDTO createNewStand() {
	return new ProcessStandBuilder()
		.withContent(STAND_CONTENT)
		.build()
		.toTransferObject();
    }

    private ProcessStand captureSavedFulfilledStand() {
	ArgumentCaptor<ProcessStand> captorProcessStand = ArgumentCaptor.forClass(ProcessStand.class);
	verify(processStandRepository).save(captorProcessStand.capture());
	
	return captorProcessStand.getValue();
    }

}
