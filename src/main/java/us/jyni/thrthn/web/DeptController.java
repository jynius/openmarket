/*
 * 
 */
package us.jyni.thrthn.web;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.jyni.thrthn.common.BaseController;
import us.jyni.thrthn.service.DeptService;
import us.jyni.thrthn.transfer.DeptFilter;
import us.jyni.thrthn.transfer.DeptForm;
import us.jyni.thrthn.transfer.DeptView;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Controller
@RequestMapping(path = "/user/dept")
public class DeptController extends BaseController {

	@Resource
	private DeptService deptService;
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(DeptFilter filter, Model model) {
		
		List<DeptView> list = deptService.findAll(filter, DeptView.class);
		model.addAttribute("page", list);
		
		return "user/dept/list";
	}
	
	@RequestMapping(path = "/tree", method = RequestMethod.GET)
	public String tree(DeptFilter filter, Model model) {
		
		Optional<DeptView> root = deptService.root(DeptView.class);
		model.addAttribute("root", !root.isPresent()? null: root.get());
		
		return "user/dept/list";
	}

	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("dept", new DeptForm());
		return "user/dept/edit";
	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String edit(
			@ModelAttribute("dept") @Valid DeptForm dept,
			BindingResult bindingResult,
			Model model) {

		if(bindingResult.hasErrors()) {
			return "user/dept/edit";
		}

		DeptView saved = deptService.save(dept, DeptView.class);

		return "redirect:edit?id=" + saved.getId();
	}
}
