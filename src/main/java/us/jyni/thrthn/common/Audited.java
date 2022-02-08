/*
 * 
 */
package us.jyni.thrthn.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true)
public class Audited<U, ID extends Serializable>
	extends AbstractPersistable<ID>
	implements Auditable<U, ID, LocalDateTime>, Serializable {

	private static final long serialVersionUID = -1893813153741205367L;
	
	@ManyToOne
	private U createdBy;
	@ManyToOne
	private U lastModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime lastModifiedDate;

	@Override
	public Optional<U> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	@Override
	public Optional<LocalDateTime> getCreatedDate() {
		return Optional.ofNullable(createdDate);
	}

	@Override
	public Optional<U> getLastModifiedBy() {
		return Optional.ofNullable(lastModifiedBy);
	}

	@Override
	public Optional<LocalDateTime> getLastModifiedDate() {
		return Optional.ofNullable(lastModifiedDate);
	}
}
