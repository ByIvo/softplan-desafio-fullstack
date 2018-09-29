package rocks.byivo.processmanager.services;

import rocks.byivo.processmanager.dto.ProcessDTO;
import rocks.byivo.processmanager.model.Process;

public interface ProcessService {
    
    Process createProcessWith(ProcessDTO newProcessData);
}
