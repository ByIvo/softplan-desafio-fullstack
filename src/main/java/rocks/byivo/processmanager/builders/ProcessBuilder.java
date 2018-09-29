package rocks.byivo.processmanager.builders;

import java.util.Date;

import rocks.byivo.processmanager.model.Process;

public class ProcessBuilder {
    
    private Process process;
    
    public ProcessBuilder() {
	process = new Process();
    }

    public ProcessBuilder withIdProcess(Long idProcess) {
	process.setIdProcess(idProcess);
	return this;
    }

    public ProcessBuilder withName(String name) {
	process.setName(name);
	return this;
    }

    public ProcessBuilder withCreatedAt(Date createdAt) {
	process.setCreatedAt(createdAt);
	return this;
    }
    
    public ProcessBuilder withUpdatedAt(Date updatedAt) {
	process.setUpdatedAt(updatedAt);
	return this;
    }
    
    public Process build() {
	return process;
    }
    
}
