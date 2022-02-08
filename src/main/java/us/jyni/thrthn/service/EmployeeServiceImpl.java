/*
 * 
 */
package us.jyni.thrthn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import us.jyni.thrthn.common.GenericRepository;
import us.jyni.thrthn.entity.Employee;
import us.jyni.thrthn.repository.EmployeeRepository;

/**
 * @author jynius
 * @Since 2020-11-01
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeRepository repository;
	
	@Override
	public GenericRepository<Employee, Integer> getRepository() {
		return repository;
	}
}
