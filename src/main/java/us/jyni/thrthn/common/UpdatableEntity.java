/*
 * 
 */
package us.jyni.thrthn.common;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author jynius
 * @Since 2020-10-29
 */
public interface UpdatableEntity<E> {

	static final Logger LOG = LoggerFactory.getLogger(UpdatableEntity.class);

	public static <E, F extends UpdatableEntity<E>> List<E> from(List<E> found, F form) {
		return found==null? null: found.stream()
			.peek(e->form.update(e))
			.collect(Collectors.toList());
	}

	public static <E, F extends UpdatableEntity<E>> E of(E entity, F form) {
		form.update(entity);
		return entity;
	}
	
	default void update(E entity) {
		BeanUtils.copyProperties(this, entity);
	}
}
