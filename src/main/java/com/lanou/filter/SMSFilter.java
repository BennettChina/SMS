package com.lanou.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
* @program: SMS消息系统
*
* @description: SMS系统过滤器
*
* @author: 吴蒙召
*
* @create: 2019-06-06 14:13
*
* @version: 1.0
**/

@WebFilter("/*")
public class SMSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 设置全局请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        // 可以在下面再添加对用户输入信息进行校对

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
