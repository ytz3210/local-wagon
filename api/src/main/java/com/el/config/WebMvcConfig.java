//package com.el.config;
//
//import com.el.interceptor.AppHandlerMethodArgumentResolver;
//import com.el.interceptor.AppInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private AppInterceptor appInterceptor;
//    @Autowired
//    private AppHandlerMethodArgumentResolver appLoginUserHandlerMethodArgumentResolver;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 所有/api/**的请求被拦截
//        registry.addInterceptor(appInterceptor).addPathPatterns("/**");
//    }
//
//    //参数解析器
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(appLoginUserHandlerMethodArgumentResolver);
//    }
//}