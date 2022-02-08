package us.jyni.thrthn.common;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public interface EntityView<E> {
	
	public static <E, T> Page<T> from(Page<E> page, Function<E, T> function) {
		return new PageImpl<T>(from(page.getContent(), function), page.getPageable(), page.getTotalElements());
	}

	public static <E, D extends EntityView<E>> Page<D> from (Page<E> page, Class<D> clazz) {
		return new PageImpl<D>(from(page.getContent(), clazz), page.getPageable(), page.getTotalElements());
	}
	
	public static <E, T> List<T> from(List<E> entities, Function<E, T> function) {
		return entities==null? null: entities.stream()
				.map(function)
				.collect(Collectors.toList());
	}
	
	public static <E, D extends EntityView<E>> List<D> from(List<E> entities, Class<D> clazz) {
		return entities==null? null: entities.stream()
				.map(e->of(e, clazz))
				.collect(Collectors.toList());
	}

	public static <E, T> Optional<T> of(Optional<E> optional, Function<E, T> function) {
		return !optional.isPresent()? Optional.empty(): Optional.of(function.apply(optional.get()));
	}
	
	public static <E, D extends EntityView<E>> Optional<D> of(Optional<E> optional, Class<D> clazz) {
		return !optional.isPresent()? Optional.empty(): Optional.of(of(optional.get(), clazz));
	}

	public static <E, D extends EntityView<E>> D empty() {
		return (D)null;
	}
	
	public static <E, D extends EntityView<E>> D of(E entity, Class<D> clazz) {
		
		try {
			D data = clazz.newInstance();
			data.setEntity(entity);
			return data;
		}
		catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	default void setEntity(E entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
