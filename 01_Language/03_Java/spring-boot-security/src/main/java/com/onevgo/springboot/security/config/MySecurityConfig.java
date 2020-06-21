package com.onevgo.springboot.security.config;

import com.onevgo.springboot.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 请求授权
                .authorizeRequests()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/root/**").access("hasRole('ADMIN') and hasRole('ROOT')")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                // 允许用户使用基于表单的登录进行身份验证
                .and().formLogin()
                // 1. 到 /login URL的POST将尝试对用户进行身份验证
                // 2. 如果查询参数 error 存在，则尝试验证并失败
                // 3. 如果查询参数 logout 存在，则用户已成功登出
                // 4. 用户名必须作为HTTP参数 name="username" 出现
                .usernameParameter("username")
                // 5. 密码必须以HTTP参数 name="password" 出现
                .passwordParameter("password")
                // 更新后的配置指定登录页面的位置。
                // 我们必须授予所有用户(即未经身份验证的用户)访问登录页面的权限。
                .loginPage("/login").permitAll();
                // 允许用户使用HTTP基本身份验证进行身份验证
                // .and().httpBasic()
                // .and().csrf()
                // .csrfTokenRepository(new HttpSessionCsrfTokenRepository());

        http
                // 处理注销
                // 使HTTP会话无效
                // 清理任何已配置的 RememberMe 身份验证
                // 清理SecurityContextHolder
                // 重定向到 /login?logout
                .logout()
                // 触发登出的URL(默认为/logout)。如果启用了CSRF保护(默认)，那么请求也必须是POST。
                // .logoutUrl("/logout")
                // 注销后要重定向到的URL。默认值是 /login?logout。
                .logoutSuccessUrl("/");
                // 让我们指定一个自定义LogoutSuccessHandler。如果指定了这一点，logoutSuccessUrl()将被忽略。
                // .logoutSuccessHandler(logoutSuccessHandler)
                // 指定是否在注销时使HttpSession无效。默认情况下这是 true 的。@see SecurityContextLogoutHandler。
                // .invalidateHttpSession(true);
                // 添加一个LogoutHandler。默认情况下，SecurityContextLogoutHandler被添加为最后一个LogoutHandler。
                // .addLogoutHandler(logoutHandler)
                // 允许指定要在注销成功时删除的cookie的名称。这是显式添加CookieClearingLogoutHandler的快捷方式。
                // .deleteCookies(cookieNamesToClear);

        http
                // 允许配置“记住我”身份验证。
                .rememberMe()
                // 用于指示在登录时记住用户的HTTP参数。
                .rememberMeParameter("remember-me");
                // 登录成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
                // 点击注销会删除cookie
    }

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // In-Memory Authentication
        // auth.inMemoryAuthentication()
        // .withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
        // .and()
        // .withUser("lisi").password("123456").roles("VIP2", "VIP3")
        // .and()
        // .withUser("wangwu").password("123456").roles("VIP1", "VIP3");

        // JDBC Authentication
        // auth.jdbcAuthentication()
        // .dataSource(dataSource)
        // .withDefaultSchema()
        // .withUser("user").password("password").roles("USER")
        // .and()
        // .withUser("admin").password("password").roles("USER", "ADMIN");

        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
