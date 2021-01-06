package net.user.block;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER_BLOCK_TABLE")
@IdClass(UserBlockPK.class)
@Data
public class UserBlockEntity {

	@Id
	@Column(name = "ID")
	private long id;

	@Id
	@Column(name = "BLOCKED_USER_ID")
	private long blockedUserId;

	public UserBlockEntity() {}

	public UserBlockEntity(long id, long blockedUserId) {
		this.id = id;
		this.blockedUserId = blockedUserId;
	}
}