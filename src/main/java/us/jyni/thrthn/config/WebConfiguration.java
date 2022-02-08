/*
 * 
 */
package us.jyni.thrthn.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import us.jyni.thrthn.Constant;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResovler() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN);
		return localeResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler(Constant.STATIC_RESOURCES)
			.addResourceLocations(Constant.STATIC_LOCATIONS)
//			.setCachePeriod(0)
			.setCacheControl(CacheControl.noStore());
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}
