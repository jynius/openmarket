package us.jyni.thrthn.web;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import us.jyni.thrthn.common.BaseController;
import us.jyni.thrthn.service.EmployeeService;

/*
 * 
 */

/**
 * @author jynius
 * @Since 2020-08-02
 */
@Controller
@RequestMapping("/user/employee")
public class EmployeeController extends BaseController {

	@Resource
	private EmployeeService employeeService;
}
