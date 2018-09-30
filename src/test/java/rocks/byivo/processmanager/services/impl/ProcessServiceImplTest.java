package rocks.byivo.processmanager.services.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.processmanager.utils.CustomMatchers.between;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.processmanager.builders.ProcessBuilder;
import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.exceptions.ProcessNotFoundException;
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
    
    @Test
    public void whenCreatingANewProcess_thenReturnTheCreatedProcess() throws Exception {
	ProcessDTO newProcessData = buildNewProcessData();
	
	Process expectedCreatedProcess = mock(Process.class);
	doReturn(expectedCreatedProcess).when(processRepository).save(Mockito.any());
	
	Process createdProcess = processService.createProcessWith(newProcessData);
	
	assertThat(createdProcess, is(expectedCreatedProcess));
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
    
    @Test
    public void givenAnId_whenRetriveAProcess_shouldReturnTheFoundProcessInRepository() throws Exception {
	long idProcess = 1L;
	Process expectedFoundProcess = mock(Process.class);
	
	when(processRepository.findById(idProcess))
		.thenReturn(Optional.of(expectedFoundProcess));
	
	Process foundProcess = processService.find(idProcess);
	
	assertThat(foundProcess, is(expectedFoundProcess));
    }
    
    @Test(expected=ProcessNotFoundException.class)
    public void givenAnUnexistingId_whenRetriveAProcess_shouldThrowsAndException() throws Exception {
	long idProcess = 1L;
	
	when(processRepository.findById(idProcess))
		.thenReturn(Optional.empty());
	
	processService.find(idProcess);
    }
    
}
