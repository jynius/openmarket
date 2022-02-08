/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-11-01
 */
@Entity
@Data
@EqualsAndHashCode
@ToString
public class Rating implements Serializable {

	private static final long serialVersionUID = -8706023425365600559L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "personId", insertable = false, updatable = false) 
	private Person person;
	private Long personId;
	
	private LocalDateTime recordedTime;
}
