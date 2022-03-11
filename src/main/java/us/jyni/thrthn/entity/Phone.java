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
public class Phone implements Serializable {

	private static final long serialVersionUID = 1816960984652587477L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String countryCode;
	private String number;

}
