package org.owpk;

import com.owpk.RabbitMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@Import(RabbitMQConfiguration.class)
public class AppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AppApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.active", "docker");
	}
}
