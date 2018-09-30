package rocks.byivo.processmanager.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.processmanager.builders.ProcessBuilder;
import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.exceptions.ProcessNotFoundException;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.repositories.ProcessRepository;
import rocks.byivo.processmanager.services.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService {

    private ProcessRepository processRepository;

    @Autowired
    public ProcessServiceImpl(ProcessRepository processRepository) {
	this.processRepository = processRepository;
    }

    @Override
    public Process createProcessWith(ProcessDTO newProcessData) {
	Process processToSave = fillNewProcessWIthContentFrom(newProcessData);
	
	return processRepository.save(processToSave);
    }

    private Process fillNewProcessWIthContentFrom(ProcessDTO newProcessData) {
	Date processOperationInstant = new Date();
	
	return new ProcessBuilder()
		.withName(newProcessData.getName())
		.withCreatedAt(processOperationInstant)
		.withUpdatedAt(processOperationInstant)
		.build();
    }

    @Override
    public Process find(Long idProcess) {
	Optional<Process> foundProcess = processRepository.findById(idProcess);
	if(foundProcess.isPresent()) {
	    return foundProcess.get();
	} else {
	    throw new ProcessNotFoundException(idProcess);
	}
    }

}
