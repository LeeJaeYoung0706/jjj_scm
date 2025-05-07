package jjj.scm.util;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("customLoggingFilter")
@Slf4j
public class CustomLoggingFilter implements Filter {
    // Servlet Filter -> Spring Container ( Component 로 등록할 때 한번 이미 Servlet Filter로 이미 등록된 상태 ) -> Security에서도 한번 더 설정을 걸었음
    // 그래서 2번탐
    // 해결 방법은
    // 1번 -> Component 를 삭제한다.
    // 2번은 filter Bean에서 제외한다.
    //
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Custom logging filter" + servletResponse.toString());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
