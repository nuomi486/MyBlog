package com.myblog.config;

import com.myblog.entity.vo.Account;
import com.myblog.entity.RestBean;
import com.myblog.filter.JetAuthorizeFilter;
import com.myblog.entity.dto.AccountDTO;
import com.myblog.service.AuthorizeService;
import com.myblog.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfiguration{

    @Resource
    JwtUtils utils;

    @Resource
    AuthorizeService authorizeService;

    @Resource
    JetAuthorizeFilter authorizeFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/auth/**").permitAll()  // 对以 "/api/auth/" 开头的请求允许所有访问
                        .requestMatchers("/upload/**").permitAll() //放行/upload/**让前端访问这个上传文件，再通过config配置类映射到本地的/upload/文件
                        .anyRequest().authenticated()  // 其他请求需要进行身份验证
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/login")  // 配置登录请求的处理URL
                        .successHandler(this::onAuthenticationHandle)  // 登录成功时的处理器
                        .failureHandler(this::onAuthenticationHandle)  // 登录失败时的处理器
                )
                .logout(conf -> conf
                        .logoutUrl("/logout")  // 配置登出请求的URL
                        .logoutSuccessHandler(this::onLogoutSuccessHandle)
                )
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::onAuthenticationHandle)  // 配置身份验证异常入口点处理器
                )
                .cors(conf -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    //添加前端站点地址，这样就可以告诉浏览器信任了
                    cors.addAllowedOrigin("http://127.0.0.1:5173/");
                    /*
                        虽然也可以像这样允许所有 cors.addAllowedOriginPattern("*");
                        但是这样并不安全，我们应该只许可给我们信任的站点
                    */
                    cors.setAllowCredentials(true);  //允许跨域请求中携带Cookie
                    cors.addAllowedHeader("*");   //其他的也可以配置，为了方便这里就 * 了
                    cors.addAllowedMethod("*");
                    cors.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);  //直接针对于所有地址生效
                    conf.configurationSource(source);
                }) /* 解决跨域问题 */
                .csrf(AbstractHttpConfigurer::disable)  // 禁用跨站请求伪造（CSRF）保护
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 配置会话管理策略为无状态
                .addFilterBefore(authorizeFilter, UsernamePasswordAuthenticationFilter.class)  // 在检查账号密码过滤器之前添加对请求头是否携带Authorize过滤器
                .build();  // 构建并返回 SecurityFilterChain 对象
    }

    /**
     * 将多种类型的Handler整合到同一个方法中，包含：
     * - 登录成功
     * - 登录失败
     * - 未登录拦截/无权限拦截
     * @param request 请求
     * @param response 响应
     * @param asAuthentication 异常或是验证实体
     * @throws IOException 可能的异常
     */
    private void onAuthenticationHandle(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Object asAuthentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (asAuthentication instanceof AuthenticationException authException){
            // 如果asAuthentication是AuthenticationException类型的实例，则将失败信息写入响应
            writer.write(RestBean.authorizeFailure(authException.getMessage()).asJsonString());
        }else if(asAuthentication instanceof Exception exception) {
            // 如果asAuthentication是Exception类型的实例，则将失败信息写入响应
            writer.write(RestBean.authorizeFailure(exception.getMessage()).asJsonString());
        } else if (asAuthentication instanceof Authentication authentication) {
            // 获取用户详细信息
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // 根据用户名获取账户信息
            AccountDTO byUser = authorizeService.findByUserName(authentication.getName());
            // 创建JWT令牌
            String jwt = utils.createJwt(userDetails,byUser);
            if(jwt == null) {
                // 如果JWT为空，则返回登录验证频繁错误
                writer.write(RestBean.authorizeFailure("登录验证频繁，请稍后再试!").asJsonString());
            } else {
                // 创建Account对象
                Account info = new Account();
                // 将账户信息里相同的属性拷贝到新的对象
                BeanUtils.copyProperties(byUser,info);
                // 设置JWT令牌
                info.setToken(jwt);
                // 设置过期时间
                info.setExpire(utils.expressTime());
                // 返回成功响应，包含Account信息
                writer.write(RestBean.success(info).asJsonString());
            }
        }
    }

    /**
     * 退出登录处理，将对应的Jwt令牌列入黑名单不再使用
     * @param request 请求
     * @param response 响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    private void onLogoutSuccessHandle(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if (utils.invalidateJet(authorization)){
            writer.write(RestBean.success().asJsonString());
        }else{
            writer.write(RestBean.authorizeFailure("退出登陆失败!").asJsonString());
        }
    }

}
