package mentoss.menmeet;

import mentoss.menmeet.filter.LogFilter;
import mentoss.menmeet.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean logFilter(){
		//서블릿 필터를 등록하기 위한 필터등록빈
		FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new LogFilter());
		filterFilterRegistrationBean.setOrder(1);//필터의 순서 체인으로 여러게 들어가니까
		filterFilterRegistrationBean.addUrlPatterns("/*");//어떤 URL패턴으로 적용할 것인지
		return filterFilterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean loginCheckFilter(){
		//서블릿 필터를 등록하기 위한 필터등록빈
		FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new LoginFilter());
		filterFilterRegistrationBean.setOrder(2);//필터의 순서 체인으로 여러게 들어가니까
		filterFilterRegistrationBean.addUrlPatterns("/*");//어떤 URL패턴으로 적용할 것인지
		return filterFilterRegistrationBean;
	}

}
