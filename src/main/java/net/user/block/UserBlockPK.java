package net.user.block;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class UserBlockPK implements Serializable {

	@Column(name = "ID")
	private long id;

	@Column(name = "BLOCKED_USER_ID")
	private long blockedUserId;

	public UserBlockPK(long id, long blockedUserId) {
		this.id = id;
		this.blockedUserId = blockedUserId;
	}
}