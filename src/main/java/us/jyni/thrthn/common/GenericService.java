/*
 * 
 */
package us.jyni.thrthn.common;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;

/**
 * @author jynius
 * @Since 2020-10-29
 */
public interface GenericService<E, I> {

	public abstract GenericRepository<E, I> getRepository();
	
	default long count(Filter<E> filter) {
		long count = getRepository().count(filter.getSpecification());
		return count;
	}

	default <T> Page<T> findPage(PageableFilter<E> filter, Function<E, T> function) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		return EntityView.from(page, function);
	}

	default <D extends EntityView<E>> Page<D> findPage(PageableFilter<E> filter, Class<D> clazz) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		return EntityView.from(page, clazz);
	}

	default <T> List<T> findAll(Filter<E> filter, Function<E, T> function) {
		List<E> list = getRepository().findAll(filter.getSpecification(), filter.getSort());
		return EntityView.from(list, function);
	}

	default <D extends EntityView<E>> List<D> findAll(Filter<E> filter, Class<D> clazz) {
		List<E> list = getRepository().findAll(filter.getSpecification(), filter.getSort());
		return EntityView.from(list, clazz);
	}

	default <T> List<T> findAllByIds(List<I> ids, Function<E, T> function) {
		List<E> list = getRepository().findAllById(ids);
		return EntityView.from(list, function);
	}

	default <D extends EntityView<E>> List<D> findAllByIds(List<I> ids, Class<D> clazz) {
		List<E> list = getRepository().findAllById(ids);
		return EntityView.from(list, clazz);
	}

	default <T> Optional<T> findOne(PageableFilter<E> filter, Function<E, T> function) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		Optional<E> entity = page.stream().findFirst();
		return EntityView.of(entity, function);
	}

	default <D extends EntityView<E>> Optional<D> findOne(PageableFilter<E> filter, Class<D> clazz) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		Optional<E> entity = page.stream().findFirst();
		return EntityView.of(entity, clazz);
	}

	default <T> Optional<T> findUnique(Filter<E> filter, Function<E, T> function) {
		Optional<E> entity = getRepository().findOne(filter.getSpecification());
		return EntityView.of(entity, function);
	}

	default <D extends EntityView<E>> Optional<D> findUnique(Filter<E> filter, Class<D> clazz) {
		Optional<E> entity = getRepository().findOne(filter.getSpecification());
		return EntityView.of(entity, clazz);
	}

	default <T> Optional<T> findById(I id, Function<E, T> function) {
		Optional<E> optional = getRepository().findById(id);
		return EntityView.of(optional, function);
	}

	default <D extends EntityView<E>> Optional<D> findById(I id, Class<D> clazz) {
		Optional<E> optional = getRepository().findById(id);
		return EntityView.of(optional, clazz);
	}

	default <T, F extends FormEntity<E>> List<T> saveAll(List<F> list, Function<E, T> function) {
		List<E> saved = getRepository().saveAll(FormEntity.from(list));
		return EntityView.from(saved, function);
	}

	default <D extends EntityView<E>, F extends FormEntity<E>> List<D> saveAll(List<F> list, Class<D> clazz) {
		List<E> saved = getRepository().saveAll(FormEntity.from(list));
		return EntityView.from(saved, clazz);
	}

	default <T, F extends UpdatableEntity<E>> List<T> changeAll(List<I> list, F form, Function<E, T> function) {
		
		List<E> found = getRepository().findAllById(list);
		if(found==null || found.isEmpty()) {
			return EntityView.from(found, function);
		}
		
		List<E> saved = getRepository().saveAll(UpdatableEntity.from(found, form));
		return EntityView.from(saved, function);
	}

	default <D extends EntityView<E>, F extends UpdatableEntity<E>> List<D> changeAll(List<I> list, F form, Class<D> clazz) {
		
		List<E> found = getRepository().findAllById(list);
		if(found==null || found.isEmpty()) {
			return EntityView.from(found, clazz);
		}
		
		List<E> saved = getRepository().saveAll(UpdatableEntity.from(found, form));
		return EntityView.from(saved, clazz);
	}

	default <T, F extends FormEntity<E>> T save(F form, Function<E, T> function) {
		E saved = getRepository().save(form.getEntity());
		return function==null? null: function.apply(saved);
	}

	default <D extends EntityView<E>, F extends FormEntity<E>> D save(F form, Class<D> clazz) {
		E saved = getRepository().save(form.getEntity());
		return EntityView.of(saved, clazz);
	}

	default <T, F extends UpdatableEntity<E>> T change(I id, F form, Function<E, T> function) {
		
		Optional<E> optional = getRepository().findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		
		E saved = getRepository().save(UpdatableEntity.of(optional.get(), form));
		return function==null? null: function.apply(saved);
	}

	default <D extends EntityView<E>, F extends UpdatableEntity<E>> D change(I id, F form, Class<D> clazz) {
		
		Optional<E> optional = getRepository().findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		
		E saved = getRepository().save(UpdatableEntity.of(optional.get(), form));
		return EntityView.of(saved, clazz);
	}

	default <F extends FormEntity<E>> int deleteAll(List<F> list) {
		getRepository().deleteAll(FormEntity.from(list));
		return list.size();
	}

	default int delete(Filter<E> filter) {
		List<E> entities = getRepository().findAll(filter.getSpecification());
		getRepository().deleteInBatch(entities);
		return entities.size();
	}

	default int deleteAllByIds(List<I> ids) {
		List<E> entities = getRepository().findAllById(ids);
		getRepository().deleteInBatch(entities);
		return entities.size();
	}

	default int deleteById(I id) {
		getRepository().deleteById(id);
		return 1;
	}
}
