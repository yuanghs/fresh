package com.fresh.interceptor;

import com.fresh.util.JwtNut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ygh
 * @date 2019/7/7
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法：直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        //得到请求的url
        String url = request.getRequestURI();
        //除了 /login.action /sendVerifyCode.action 可以访问，其他的不可以访问
        if(url.contains("/login") || url.contains("sendVerifyCode")) {
            return true;
        }

//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();

        // 从header中得到token
        String token = request.getHeader("authorize");

        //在每个请求来之前都需要带着token串：验证token串
        JwtNut jwtNut = new JwtNut();
        jwtNut.init("heeeyou", "chestnut&youyinnn");
        if (jwtNut.verify(token) != true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
