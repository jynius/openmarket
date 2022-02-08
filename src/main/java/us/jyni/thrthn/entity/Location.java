/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Location implements Serializable {

	private static final long serialVersionUID = -3264472840312921891L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	private List<Coordinate> coordinate;
	@ManyToMany
	private List<Address> address;
	@ManyToMany
	private List<Phone> phone;
}
