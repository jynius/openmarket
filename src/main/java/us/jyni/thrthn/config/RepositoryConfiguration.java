/*
 * 
 */
package us.jyni.thrthn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("us.jyni.thrthn.repository")
public class RepositoryConfiguration {

}
