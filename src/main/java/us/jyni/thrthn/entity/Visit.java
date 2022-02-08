/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
public class Visit implements Serializable {

	private static final long serialVersionUID = -2912815847820775390L;
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "personId") 
	private Person person;
	@ManyToOne
	@JoinColumn(name = "placeId") 
	private Place place;
	
	@ManyToMany
	@JoinColumn(name = "personId") 
	private List<Person> companions;
	
	private LocalDateTime inTime;
	private LocalDateTime outTime;
}
