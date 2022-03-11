/*
 * 
 */
package us.jyni.thrthn.transfer;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import us.jyni.thrthn.common.Filter;
import us.jyni.thrthn.entity.Dept;

/**
 * @author jynius
 * @Since 2020-08-01
 */
public class DeptFilter implements Filter<Dept>, Serializable {

	private static final long serialVersionUID = -9147591089759117730L;

	/**
	 * @return
	 */
	public Pageable getPageable() {
		return PageRequest.of(0, 10, Sort.unsorted());
	}

	@Override
	public Specification<Dept> getSpecification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}
}
