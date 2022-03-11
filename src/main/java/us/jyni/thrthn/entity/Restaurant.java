/*
 * 
 */
package us.jyni.thrthn.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Restaurant extends Place {

	private static final long serialVersionUID = -8181310060434679812L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "foodId", insertable = false, updatable = false)
	private List<Food> foods;
}
