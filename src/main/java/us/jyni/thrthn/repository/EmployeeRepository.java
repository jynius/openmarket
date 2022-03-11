/*
 * 
 */
package us.jyni.thrthn.repository;

import org.springframework.stereotype.Repository;

import us.jyni.thrthn.common.GenericRepository;
import us.jyni.thrthn.entity.Employee;

/**
 * @author jynius
 * @Since 2020-10-31
 */
@Repository
public interface EmployeeRepository extends GenericRepository<Employee, Integer> {

}
