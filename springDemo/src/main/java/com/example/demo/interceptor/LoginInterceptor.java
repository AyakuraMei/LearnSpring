package com.example.demo.interceptor;

import com.example.demo.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor{
    // 查询是否是拦截的路径
    private boolean begingWith(String page, String[] requireAuthPages){
        boolean result = false;
        for(String requireAuthPage: requireAuthPages){
            if(StringUtils.startsWith(page, requireAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }

    // 可能会引发多次重定向问题
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        // 需要权限才可以访问的页面
        String[] requireAuthPages = new String[]{
                "index",
        };
        String uri = request.getRequestURI();
        // 在字符串中移除想要访问的页面
        uri = StringUtils.remove(uri, contextPath + "/");
        String page = uri;
        if(begingWith(page, requireAuthPages)){
            // 判断 session 中是否存在 user，如果存在那么久可以通过，否则就转会到 \login 界面
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }
}
