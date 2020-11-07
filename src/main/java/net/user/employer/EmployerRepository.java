package net.user.employer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository
		extends JpaRepository<EmployerEntity, Long>,
		JpaSpecificationExecutor<EmployerEntity> {
}