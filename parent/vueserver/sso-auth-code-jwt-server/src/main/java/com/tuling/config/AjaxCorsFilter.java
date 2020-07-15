package com.tuling.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/13 0:41
 * @Version V1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AjaxCorsFilter extends CorsFilter {
    public AjaxCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
        List<String> allowedOrigins = Arrays.asList("*");
        corsConfig.setAllowedHeaders(allowedHeaders);
        corsConfig.setAllowedMethods(allowedMethods);
        corsConfig.setAllowedOrigins(allowedOrigins);
        corsConfig.setExposedHeaders(exposedHeaders);
        corsConfig.setMaxAge(36000L);
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
