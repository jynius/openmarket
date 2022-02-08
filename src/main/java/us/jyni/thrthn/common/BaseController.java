/*
 * 
 */
package us.jyni.thrthn.common;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import us.jyni.thrthn.util.TimeFilledLocalDateTimeEditor;

/**
 * @author jynius
 * @Since 2020-08-06
 */
public class BaseController {

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		PropertyEditor propertyEditor = new TimeFilledLocalDateTimeEditor("00:00:00");
	    binder.registerCustomEditor(LocalDateTime.class, propertyEditor);
	}
}
