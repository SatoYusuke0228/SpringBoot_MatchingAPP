package net.user.talk_request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class TalkRequestPK implements Serializable {


	@Column(name = "ID")
	private long id;

	@Column(name = "REQUESTED_USER_ID")
	private long requestedUserId;

	public TalkRequestPK() {
	}

	public TalkRequestPK(long id, long requestedUserId) {
		this.id = id;
		this.requestedUserId = requestedUserId;
	}
}