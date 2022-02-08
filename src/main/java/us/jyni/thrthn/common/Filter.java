/*
 * 
 */
package us.jyni.thrthn.common;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author jynius
 * @Since 2020-10-29
 */
public interface Filter<E> {

	public Specification<E> getSpecification();
	
	public Sort getSort();
}
