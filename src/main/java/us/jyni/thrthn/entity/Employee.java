/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-10-31
 */
@Entity
@Data
@EqualsAndHashCode
@ToString
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 6319010584792978896L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "deptId", insertable = false, updatable = false) 
	private Dept dept;
	private Integer deptId;
	
	private String name;
	private String title;
}
