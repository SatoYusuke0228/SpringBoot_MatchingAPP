package net.user.thumbnail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbnailRepository extends
		JpaRepository<ThumbnailEntity, Long>,
		JpaSpecificationExecutor<ThumbnailEntity> {
}