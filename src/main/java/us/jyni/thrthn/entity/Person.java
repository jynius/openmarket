/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
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
@Data
@EqualsAndHashCode
@ToString
public class Person implements Serializable {

	private static final long serialVersionUID = -4492176950258704297L;
	
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	@JoinColumn(name = "visitId")
	private List<Visit> visits;
	@OneToMany
	@JoinColumn(name = "ratingId")
	private List<Rating> ratings;
	@OneToMany
	@JoinColumn(name = "ratingId")
	private List<ImageFile> images;
}
