package com.cdutcm.jly;

import java.util.HashMap;
import java.util.Map;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cdutcm.core.shiro.beetl.ShiroExt;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.cdutcm.core.shiro", "com.cdutcm"})
public class Application  extends SpringBootServletInitializer {

//	@ComponentScan("com.cdutcm")
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(Application.class);
	}
	
	/**
	 * 加入beetl启动项
	 * @return
	 */
	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			ClasspathResourceLoader cploder = new ClasspathResourceLoader(
					"templates/");
			beetlGroupUtilConfiguration.setResourceLoader(cploder);

			beetlGroupUtilConfiguration.setConfigFileResource(patternResolver
					.getResource("classpath:beetl.properties"));
			Map<String, Object> functionPackages=new HashMap<String, Object>();
  			functionPackages.put("so", new ShiroExt());
  			beetlGroupUtilConfiguration.setFunctionPackages(functionPackages);
			return beetlGroupUtilConfiguration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(
			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setViewNames("*.html");
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}

}
