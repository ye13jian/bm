/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eluoen.bm.config.web;

import cn.stylefeng.roses.core.xss.XssFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.eluoen.bm.config.properties.GunsProperties;
import com.eluoen.bm.core.common.controller.GunsErrorView;
import com.eluoen.bm.core.interceptor.RestApiInteceptor;
import com.eluoen.bm.core.listener.ConfigListener;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * web 配置类
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 增加swagger的支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (gunsProperties.getSwaggerOpen()) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    /**
     * 增加对rest api鉴权的spring mvc拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/gunsApi/**");
    }

    /**
     * 默认错误页面，返回json
     */
    @Bean("error")
    public GunsErrorView error() {
        return new GunsErrorView();
    }

    /**
     * druidServlet注册
     */
    @Bean
    public ServletRegistrationBean druidServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet());
        registration.addUrlMappings("/druid/*");
        return registration;
    }

    /**
     * druid监控 配置URI拦截策略
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter(
                "exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
        //用于session监控页面的用户名显示 需要登录后主动将username注入到session里
        filterRegistrationBean.addInitParameter("principalSessionName", "username");
        return filterRegistrationBean;
    }

    /**
     * druid数据库连接池监控
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.eluoen.book.modular.*.service.*";
        //可以set多个
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }

    /**
     * druid数据库连接池监控
     */
    @Bean
    public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
        BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
        beanTypeAutoProxyCreator.setTargetBeanType(DruidDataSource.class);
        beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanTypeAutoProxyCreator;
    }

    /**
     * druid 为druidStatPointcut添加拦截
     *
     * @return
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }

    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        XssFilter xssFilter = new XssFilter();
        xssFilter.setUrlExclusion(Arrays.asList("/notice/update", "/notice/add"));
        FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    /**
     * ConfigListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new ConfigListener());
    }

    /**
     * 验证码生成相关
     */
    @Bean
    public DefaultKaptcha kaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "105,179,90");
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.image.width", "125");
        properties.put("kaptcha.image.height", "45");
        properties.put("kaptcha.textproducer.font.size", "45");
        properties.put("kaptcha.session.key", "code");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


    /**
     * 使用Springboot集成wpsoffice在线编辑保存时获取不到流。
     * 在Springboot程序启动后，会默认添加OrderedCharacterEncodingFilter和HiddenHttpMethodFilter过滤器。
     * 在HiddenHttpMethodFilter过滤器中会调用request.getParameter(),从而造成我们在controller中通过request的InputStream无法读取到RequestBody的数据。
     * eluoen 20181219。这个问题是在测试上传文件到oss然后回调时发现的，回调获取不到inputstream流
     * 解决方法参见 https://segmentfault.com/a/1190000016406484?utm_source=tag-newest
     * 也就是新增本方法
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter(){
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                String requestURI = request.getRequestURI();
                //String requestURL = request.getRequestURL().toString();
                //logger.info("requestURI:"+requestURI);
                //logger.info("requestURL:"+requestURL);

                //暂时只在上传oss回调时使用
                //ossCallBack不能随便改，要配合controllr中的名称
                if(requestURI.contains("ossCallBack")){
                    // 防止流读取一次后就没有了, 所以需要将流继续写出去
                    ServletRequest requestWrapper = new   BodyReaderHttpServletRequestWrapper(request);
                    filterChain.doFilter(requestWrapper, response);
                }else{
                    filterChain.doFilter(request, response);
                }

            }
        };
    }

}
