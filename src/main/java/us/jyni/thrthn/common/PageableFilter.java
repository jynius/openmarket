/*
 * 
 */
package us.jyni.thrthn.common;

import org.springframework.data.domain.Pageable;

/**
 * @author jynius
 * @Since 2020-10-29
 */
public interface PageableFilter<E> extends Filter<E>, Pageable {

	public Pageable getPageable();
}
