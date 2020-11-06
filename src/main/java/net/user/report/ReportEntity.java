package net.user.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Table(name = "REPORT_TABLE")
@Data
@Transactional
public class ReportEntity {

	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "REPORTER_USER")
	private long reporterUser;

	@Column(name = "REPORTED_USER")
	private long reportedUser;

	public ReportEntity() {};
}