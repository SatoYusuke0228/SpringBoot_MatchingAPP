package net.user.engineer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Table(name = "ENGINEER_TABLE")
@Data
@Transactional
public class EngineerEntity {

	@Id
	@Column(name = "ID", unique = true)
	private long id;
}
