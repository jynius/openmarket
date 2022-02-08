/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-11-01
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Data
@EqualsAndHashCode
@ToString
public class Place implements Serializable {

	private static final long serialVersionUID = 5109146909665147664L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
	private List<Location> location;
	@OneToMany
	@JoinColumn(name = "visitId", insertable = false, updatable = false)
	private List<Visit> visits;
	@OneToMany
	@JoinColumn(name = "ratingId", insertable = false, updatable = false)
	private List<Rating> ratings;
	@OneToMany
	@JoinColumn(name = "ratingId", insertable = false, updatable = false)
	private List<ImageFile> images;
	
	private String name;
}
