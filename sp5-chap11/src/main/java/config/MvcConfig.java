package config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import interceptor.AuthCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}

	@Bean
	public MessageSource messageSource() { // spring:message태그를 사용하기위해 문자열 등록
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) { // 인터셉터를 설정하는 메서드
		// addInterceptor메서드는 HandlerInterceptor객체를 설정함
		// AutoCheckInterceptor객체를 인터셉터로 지정
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**")
				.excludePathPatterns("/edit/help/**");
		// /edit/ 로 시작하는 모든경로에 인터셉터 지정
		// 만약 지정한 경로패턴중 일부분을 제외하고 싶을경우 excludePathPatterns()를 쓰면 됨

	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}

	// 프로젝트의 모든 날짜형식을 한꺼번에 지정하는 메서드
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
//				.json()
//				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//				//유닉스 타임스탬프로 출력하는 기능을 비활성화 함으로써 ISO-8601형식으로 출력하게 됨
//				.build();

		// 모든 LocalDateTime타입에 내가 원하는 패턴으로 변환하고 싶을 때, 아래처럼 직접설정하면 됨
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter)).build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}

}
