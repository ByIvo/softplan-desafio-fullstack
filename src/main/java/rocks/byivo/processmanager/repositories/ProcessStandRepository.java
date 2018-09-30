package rocks.byivo.processmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.byivo.processmanager.model.ProcessStand;

public interface ProcessStandRepository extends JpaRepository<ProcessStand, Long> {

}
