package com.myblog.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.myblog.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JetAuthorizeFilter extends OncePerRequestFilter {
    @Resource
    JwtUtils utils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = utils.resolveJwt(authorization);
        if (jwt != null) {
            // 通过 utils.UserDetails解析jwt 用户信息，得到UserDetails 对象
            UserDetails user = utils.resolveUser(jwt);
            // 创建一个 UsernamePasswordAuthenticationToken 对象，用于表示基于用户名和密码的身份验证
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // 设置额外的身份验证细节，包括请求的详细信息
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将身份验证对象设置到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 将 "id" 属性设置到请求中，属性值由 jwt提供的信息通过utils.resolveId解析返回
            request.setAttribute("id", utils.resolveId(jwt));
        }
        // 调用 filterChain.doFilter() 方法，将请求和响应传递给下一个过滤器或目标资源进行处理
        filterChain.doFilter(request,response);
    }
}
