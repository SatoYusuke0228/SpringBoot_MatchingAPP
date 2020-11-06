package net.user.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository
		extends JpaRepository<ReportEntity, Long>,
		JpaSpecificationExecutor<ReportEntity> {
}