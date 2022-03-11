/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Food implements Serializable {

	private static final long serialVersionUID = 2504816828794774808L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;
	
	@OneToMany
	@JoinColumn(name = "ratingId", insertable = false, updatable = false)
	private List<Rating> ratings;
	@OneToMany
	@JoinColumn(name = "ratingId", insertable = false, updatable = false)
	private List<ImageFile> images;

	private String name;
}
