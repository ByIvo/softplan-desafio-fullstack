package rocks.byivo.processmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.byivo.processmanager.model.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {

}
