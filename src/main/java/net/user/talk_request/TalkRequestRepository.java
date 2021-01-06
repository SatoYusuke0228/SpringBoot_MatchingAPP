package net.user.talk_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRequestRepository
		extends JpaRepository<TalkRequestEntity, TalkRequestPK>,
		JpaSpecificationExecutor<TalkRequestEntity> {
}