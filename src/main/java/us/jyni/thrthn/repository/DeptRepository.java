/*
 * 
 */
package us.jyni.thrthn.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import us.jyni.thrthn.common.GenericRepository;
import us.jyni.thrthn.entity.Dept;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Repository
public interface DeptRepository extends GenericRepository<Dept, Integer> {

	Optional<Dept> findByParentIdNull();
}
