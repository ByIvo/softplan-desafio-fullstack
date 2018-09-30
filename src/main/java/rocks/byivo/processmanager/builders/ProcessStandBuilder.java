package rocks.byivo.processmanager.builders;

import java.util.Date;

import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.model.ProcessStand;

public class ProcessStandBuilder {

    private ProcessStand processStand;

    public ProcessStandBuilder() {
	processStand = new ProcessStand();
    }

    public ProcessStandBuilder withIdProcessStand(Long idProcessStand) {
	processStand.setIdProcessStand(idProcessStand);
	return this;
    }

    public ProcessStandBuilder withContent(String content) {
	processStand.setContent(content);
	return this;
    }

    public ProcessStandBuilder withCreatedAt(Date createdAt) {
	processStand.setCreatedAt(createdAt);
	return this;
    }

    public ProcessStandBuilder withUpdatedAt(Date updatedAt) {
	processStand.setUpdatedAt(updatedAt);
	return this;
    }

    public ProcessStandBuilder withProcess(Process process) {
	processStand.setProcess(process);
	return this;
    }

    public ProcessStand build() {
	return processStand;
    }

}
