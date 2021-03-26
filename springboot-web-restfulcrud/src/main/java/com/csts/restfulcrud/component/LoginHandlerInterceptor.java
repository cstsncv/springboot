package com.csts.restfulcrud.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null){
            //用户未登陆, 返回登陆页面
            request.setAttribute("msg", "没有权限, 请登陆");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            // 已登录, 放行
            return true;
        }
    }
}
