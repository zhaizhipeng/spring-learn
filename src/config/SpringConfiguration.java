package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring 的配置类，相当于 bean.xml
 */
@Configuration
@ComponentScan(value = "com.ysdrzp")
@Import({ DataSourceConfig.class})
public class SpringConfiguration {
}
