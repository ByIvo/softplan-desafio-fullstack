package rocks.byivo.processmanager.dto;

import java.util.Date;

public class ProcessStandDTO {

    private Long idProcessStand;

    private String content;

    private Date createdAt;

    private Long idProcess;

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

    public Long getIdProcess() {
	return idProcess;
    }

    public void setIdProcess(Long idProcess) {
	this.idProcess = idProcess;
    }

}
