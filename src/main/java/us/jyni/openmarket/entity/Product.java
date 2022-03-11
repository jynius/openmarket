/**
 * 
 */
package us.jyni.openmarket.entity;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

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
public class Product extends BaseEntity<Long> {

	private static final long serialVersionUID = 3999936291511354824L;
	
    @ElementCollection
    @CollectionTable(
		name = "product_category_mapping", 
		joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "id")
    })
    @MapKeyColumn(name = "item_name")
	private Map<Category.Group, Category> categoryMap;
}
