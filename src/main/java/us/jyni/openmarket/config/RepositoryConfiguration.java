/*
 * 
 */
package us.jyni.openmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import us.jyni.openmarket.Constant;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(Constant.REPOSITORIES_PATH)
public class RepositoryConfiguration {

}
