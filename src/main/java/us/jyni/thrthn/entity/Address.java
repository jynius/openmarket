/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Address implements Serializable {

	private static final long serialVersionUID = -6424579114761377825L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String country;
	private String zipCode;
	private String state;
	private String city;
	private String road;
	private String building;
	private String number;
}
