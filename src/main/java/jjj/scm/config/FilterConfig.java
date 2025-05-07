package jjj.scm.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final Filter filter;

    public FilterConfig(@Qualifier("customLoggingFilter") Filter loggingFilter) {
        this.filter = loggingFilter;
    }

    /**
     * Spring Container 에 Bean을 등록하는 시점에 Bean Filter 를 제외하도록 설정
     * Spring Security FilterChain 에서 사용하기 때문에
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
}
