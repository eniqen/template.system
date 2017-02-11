package org.bitbucket.eniqen.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Id
	@Column(name = "id", updatable = false, nullable = false, unique = true, length = 36)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Version
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
