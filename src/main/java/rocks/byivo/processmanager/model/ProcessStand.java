package rocks.byivo.processmanager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import rocks.byivo.processmanager.dto.ProcessStandDTO;

@Entity
public class ProcessStand {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProcessStand;
    
    private String content;
    
    private Date createdAt;
    
    private Date updatedAt;
    
    @ManyToOne
    @JoinColumn(name="idProcess")
    private Process process;

    public Long getIdProcessStand() {
        return idProcessStand;
    }

    public void setIdProcessStand(Long idProcessStand) {
        this.idProcessStand = idProcessStand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
    
    public ProcessStandDTO toTransferObject() {
	ProcessStandDTO processStandDTO = newDTOWithProcessInfo();
	
	processStandDTO.setIdProcessStand(this.getIdProcessStand());
	processStandDTO.setContent(this.getContent());
	processStandDTO.setCreatedAt(this.getCreatedAt());

	return processStandDTO;
    }

    private ProcessStandDTO newDTOWithProcessInfo() {
	ProcessStandDTO processStandDTO = new ProcessStandDTO();
	
	if(this.getProcess() != null) {
	    processStandDTO.setIdProcess(this.getProcess().getIdProcess());
	}
	
	return processStandDTO;
    }
    
}
