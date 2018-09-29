package rocks.byivo.processmanager.dto;

public class ProcessDTO {
    
    private Long idProcess;
    
    private String name;

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idTask) {
        this.idProcess = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String titleName) {
        this.name = titleName;
    }
    
}
