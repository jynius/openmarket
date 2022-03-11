/*
 * 
 */
package us.jyni.thrthn.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;

/**
 * @author jynius
 * @Since 2020-08-09
 */
//@Configuration
public class TomcatConfiguration {

	/**
	 * @return
	 */
	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {

		return new TomcatServletWebServerFactory() {

			@Override
			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {

				// tomcat.enableNaming();
				super.addAdditionalTomcatConnectors(connector());

				return super.getTomcatWebServer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {

				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");

				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				securityConstraint.addCollection(collection);

				context.addConstraint(securityConstraint);

				super.postProcessContext(context);
			}
		};
	}

	/**
	 * @return
	 */
	private Connector connector() {

		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
}
