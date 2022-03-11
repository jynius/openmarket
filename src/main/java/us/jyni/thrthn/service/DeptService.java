/*
 * 
 */
package us.jyni.thrthn.service;

import java.util.Optional;

import us.jyni.thrthn.common.EntityView;
import us.jyni.thrthn.common.GenericService;
import us.jyni.thrthn.entity.Dept;

/**
 * @author jynius
 * @Since 2020-08-01
 */
public interface DeptService extends GenericService<Dept, Integer> {

	public <D extends EntityView<Dept>> Optional<D> root(Class<D> clazz);
}
