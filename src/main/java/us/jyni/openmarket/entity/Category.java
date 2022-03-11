/**
 * 
 */
package us.jyni.openmarket.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import us.jyni.frame.jpa.BaseEntity;

/**
 * @author jynius
 *
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString
public class Category extends BaseEntity<Long> {

	private static final long serialVersionUID = 2567859447114804397L;
	
	public enum Group {
		
	}

	private Category parent;
	private List<Category> children;
} 
