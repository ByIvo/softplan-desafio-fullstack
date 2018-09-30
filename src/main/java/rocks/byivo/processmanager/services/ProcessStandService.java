package rocks.byivo.processmanager.services;

import rocks.byivo.processmanager.dto.ProcessStandDTO;
import rocks.byivo.processmanager.model.Process;
import rocks.byivo.processmanager.model.ProcessStand;

public interface ProcessStandService {

    ProcessStand standForProcess(Process process, ProcessStandDTO newProcessStand);

}
