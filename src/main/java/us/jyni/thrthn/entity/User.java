/*
 * 
 */
package us.jyni.thrthn.entity;

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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends Person {

	private static final long serialVersionUID = -4569856421239871412L;
	
	@Id
	@GeneratedValue
	private Long id;

	private String userid;
	private String passwd;
}
