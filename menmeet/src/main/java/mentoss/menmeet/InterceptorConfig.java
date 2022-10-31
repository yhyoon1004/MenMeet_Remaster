package mentoss.menmeet;

import mentoss.menmeet.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)//순서
				.addPathPatterns("/**")//적용할 주소
				.excludePathPatterns("/css/**","/*.ico","/error");//제외할 주소

	}
}
