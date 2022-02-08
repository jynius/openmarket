/*
 * 
 */
package us.jyni.thrthn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.jyni.thrthn.common.BaseController;

/**
 * @author jynius
 * @Since 2020-08-08
 */
@Controller
@RequestMapping("/place")
public class PlaceController extends BaseController {

	@RequestMapping(path = "search", method = RequestMethod.GET)
	public String search(Model model) {
		return "place/search";
	}
}
