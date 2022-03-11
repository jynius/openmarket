package us.jyni.thrthn.common;

import java.util.List;
import java.util.stream.Collectors;

public interface FormEntity<E> {

	public static <E, D extends FormEntity<E>> List<E> from(List<D> views) {
		return views==null? null: views.stream()
				.filter(FormEntity::valid)
				.map(FormEntity::getEntity)
				.collect(Collectors.toList());
	}

	public E getEntity();

	default boolean valid() {
		return true;
	}
}
