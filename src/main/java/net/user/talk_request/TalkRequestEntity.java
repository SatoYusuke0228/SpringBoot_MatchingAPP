package net.user.talk_request;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TALK_REQUEST_TABLE")
@IdClass(TalkRequestPK.class)
@Data
public class TalkRequestEntity {

	@Id
	@Column(name = "ID")
	private long id;

	@Id
	@Column(name = "REQUESTED_USER_ID")
	private long requestedUserId;

	@Column(name = "REQUEST_DATE", nullable = false)
	private Timestamp requestDate;

	@Column(name = "MATCHING")
	private boolean matching;

	@Column(name = "MATCHING_DATE")
	private Timestamp matchingDate;

	public TalkRequestEntity() {
	}

	public TalkRequestEntity(TalkRequestPK pk) {

		this.id = pk.getId();
		this.requestedUserId = pk.getRequestedUserId();
		this.requestDate = new Timestamp(System.currentTimeMillis());
		this.matching = false;
		this.matchingDate = null;
	}
}