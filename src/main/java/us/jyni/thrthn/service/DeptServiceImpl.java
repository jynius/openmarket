/*
 * 
 */
package us.jyni.thrthn.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import us.jyni.thrthn.common.EntityView;
import us.jyni.thrthn.common.GenericRepository;
import us.jyni.thrthn.entity.Dept;
import us.jyni.thrthn.repository.DeptRepository;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	private DeptRepository repository;

	@Override
	public GenericRepository<Dept, Integer> getRepository() {
		return repository;
	}

	@Override
	public <D extends EntityView<Dept>> Optional<D> root(Class<D> clazz) {
		Optional<Dept> optional = repository.findByParentIdNull();
		return EntityView.of(optional, clazz);
	}
}
