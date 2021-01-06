package net.user.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlockRepository
		extends JpaRepository<UserBlockEntity, UserBlockPK>,
		JpaSpecificationExecutor<UserBlockEntity> {
}