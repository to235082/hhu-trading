package com.hhu.tradingplatform.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/register", "/").permitAll()  // 这些路径不需要认证
                .anyRequest().authenticated()  // 其他路径都需要认证
                .and()
                .formLogin()  // 使用默认的表单登录
                .loginPage("/login")  // 自定义登录页面
                .permitAll()  // 登录页对所有人开放
                .and()
                .logout()  // 默认的注销配置
                .permitAll();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // 使用内存中的用户数据，这里创建一个测试用户，用户名为 admin，密码为 password
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("{noop}password").roles("USER").build());
        return manager;
    }
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

}

