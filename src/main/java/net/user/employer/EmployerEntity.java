package net.user.employer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Table(name = "EMPLOYER_TABLE")
@Data
@Transactional
public class EmployerEntity {

	@Id
	@Column(name = "ID", unique = true)
	private long id;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "COMPANY_URL")
	private String companyURL;

	@Column(name = "SELF_PR")
	private String selfPR;

	public EmployerEntity() {
	}

	public EmployerEntity(long id, Employer employer) {

		this.id = id;
		this.companyName = employer.getCompanyName();
		this.companyURL = employer.getCompanyURL();
		this.selfPR = employer.getSelfPR();
	}
}