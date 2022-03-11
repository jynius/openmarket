/*
 * 
 */
package us.jyni.openmarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import us.jyni.openmarket.Constant;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${thrthn.security.debug.enabled:false}")
	private boolean debugEnabled;

	@Value("${thrthn.security.csrf.disable:false}")
	private boolean csrfDisable;

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers(Constant.STATIC_RESOURCES);
		web.debug(debugEnabled);
		
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(csrfDisable) {
			http.csrf().disable();
		}
		
		super.configure(http);
	}
}
