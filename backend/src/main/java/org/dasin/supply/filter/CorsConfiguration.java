package org.dasin.supply.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 该类用于跨域使用
 * @author Parallel
 *
 */

@Component
public class CorsConfiguration extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 在服务器响应客户端的时候，带上Access-Control-Allow-Origin信息，允许特定的域名访问
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Max-Age", "3600");//60 min
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);

    }

}
