package rocks.byivo.processmanager.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocks.byivo.processmanager.dto.ProcessStandDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.model.ProcessStand;
import rocks.byivo.processmanager.services.ProcessService;
import rocks.byivo.processmanager.services.ProcessStandService;

@RestController
@RequestMapping("processes/{idProcess}/stands")
public class ProcessStandController {

    private ProcessStandService processStandService;
    
    private ProcessService processService;
    
    @Autowired
    public ProcessStandController(ProcessStandService processStandService, ProcessService processService) {
	this.processStandService = processStandService;
	this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<ProcessStandDTO> createNewStand(
	    @PathVariable("idProcess") Long idProcess, @RequestBody ProcessStandDTO newProcessStand) {
	Process foundProcess = processService.find(idProcess);
	
	ProcessStand createdStand = processStandService.standForProcess(foundProcess, newProcessStand);
	
	return new ResponseEntity<>(createdStand.toTransferObject(), CREATED);
    }
}
