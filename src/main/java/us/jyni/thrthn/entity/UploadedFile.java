/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode
@ToString
public class UploadedFile implements Serializable {

	private static final long serialVersionUID = 2805808212884315041L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "personId", insertable = false, updatable = false) 
	private Person person;

	private String path;
	private String type;
	private String name;
	private long size;

	private LocalDateTime createdTime;
}
