package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration 
@ComponentScan("spitter.web") //启用组件扫描
@EnableWebMvc  //启用SpringMVC
public class WebConfig extends WebMvcConfigurerAdapter{
	//配置JSP视图解析器
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	//配置静态资源的处理
	//要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上， 而不是使用DispatcherServlet本身来处理此类请求
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
