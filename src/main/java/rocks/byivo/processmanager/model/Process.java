package rocks.byivo.processmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import rocks.byivo.processmanager.dto.ProcessDTO;

@Entity
@Table(name = "processes")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcess;
    
    @Column(length=50, nullable=false)
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date updatedAt;

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ProcessDTO toTransferObject() {
	ProcessDTO processDTO = new ProcessDTO();
	
	processDTO.setIdProcess(this.getIdProcess());
	processDTO.setName(this.getName());
	
	return processDTO;
    }
    
}
