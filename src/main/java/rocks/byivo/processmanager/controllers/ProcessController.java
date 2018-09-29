package rocks.byivo.processmanager.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.services.ProcessService;

@RestController
@RequestMapping("/processes")
public class ProcessController {
    
    private ProcessService processService;
    
    @Autowired
    public ProcessController(ProcessService processService) {
	this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<ProcessDTO> create(@RequestBody ProcessDTO newProcessFromClient) {
	Process createdProcess = processService.createProcessWith(newProcessFromClient);
	return new ResponseEntity<>(createdProcess.toTransferObject(), CREATED);
    }
}
