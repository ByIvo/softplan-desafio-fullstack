package rocks.byivo.processmanager.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.processmanager.builders.ProcessStandBuilder;
import rocks.byivo.processmanager.dto.ProcessStandDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.model.ProcessStand;
import rocks.byivo.processmanager.repositories.ProcessStandRepository;
import rocks.byivo.processmanager.services.ProcessStandService;

@Service
public class ProcessStandServiceImpl implements ProcessStandService {
    
    private ProcessStandRepository processStandRepository;
    
    @Autowired
    public ProcessStandServiceImpl(ProcessStandRepository processStandRepository) {
	this.processStandRepository = processStandRepository;
    }

    @Override
    public ProcessStand standForProcess(Process process, ProcessStandDTO newProcessStand) {
	ProcessStand standToBeCreated = buildANewStandWith(process, newProcessStand);
	
	return processStandRepository.save(standToBeCreated);
    }

    private ProcessStand buildANewStandWith(Process process, ProcessStandDTO newProcessStand) {
	Date processMoment = new Date();
	ProcessStand standToBeCreated = new ProcessStandBuilder()
		.withContent(newProcessStand.getContent())
		.withProcess(process)
		.withCreatedAt(processMoment)
		.withUpdatedAt(processMoment)
		.build();
	return standToBeCreated;
    }

}
