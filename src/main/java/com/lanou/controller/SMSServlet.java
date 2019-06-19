package com.lanou.controller;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.Message;
import com.lanou.bean.User;
import com.lanou.service.SMSService;
import com.lanou.service.serviceimpl.SMSServiceImpl;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@MultipartConfig
@WebServlet(name = "SMSServlet", urlPatterns = {"/login", "/register", "/exit",
        "/sendMessage", "/goSendMessage", "/readMessage", "/deleteMessage", "/getMessage"})
public class SMSServlet extends HttpServlet {

    // 实例化业务逻辑类
    private static SMSService SMSService;

    static {
        SMSService = new SMSServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "exit":
                exit(req, resp);
                break;
            case "goSendMessage":
                User us = (User) req.getSession().getAttribute("user");
                // 找出所有的用户
                List<User> userList = SMSService.searchAll(us);
                // 查找成功则将结果加到request作用域中，并转发到发消息的界面
                if (!userList.isEmpty()){
                    req.setAttribute("userList",userList);
                    req.getRequestDispatcher("sendMessage.jsp").forward(req,resp);
                }
                break;
            case "reply":
                reply(req,resp);
                break;
            case "readMessage":
                readMessage(req,resp);
                break;
            case "deleteMessage":
                deleteMessage(req,resp);
                break;
            case "getMessage":
                getMessage(req,resp);
            default:
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().println("<script>alert('sorry,您的操作不合法！！！');window.history.back();</script>");
                break;
        }
    }

    /**
    * @Description: Ajax获取消息的json字符串
    * @Param: [req, resp]
    * @Return: void
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    private void getMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取session中的用户信息
        User user = (User) req.getSession().getAttribute("user");
        // 获取用户的消息
        List<Message> messageList = SMSService.searchMessage(user);
        if (!messageList.isEmpty()){
            // 转化为json字符串并输出给前台
            String data = JSON.toJSONString(messageList);
            resp.getWriter().println(data);
            resp.getWriter().close();
        }
    }

    /**
    * @Description: 删除消息(伪删除)
    * @Param: [req, resp]
    * @Return: void
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    private void deleteMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取消息的id
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (id != null){
            // 实例化Message类并为消息的id和status赋值
            Message message = new Message();
            message.setId(id);
            message.setStatus(Message.STATUS_DELETED);
            // 获取session中的User
            User user = (User) req.getSession().getAttribute("user");
            // 调用方法删除消息（伪删除）
            int row = SMSService.update(message);
            if (row != 0){
                // 删除消息成功则查出当前用户的消息并返回至main.jsp
                List<Message> messageList = SMSService.searchMessage(user);
                req.setAttribute("messageList",messageList);
                req.getRequestDispatcher("main.jsp").forward(req,resp);
            }
        }
    }

    /**
    * @Description: 回复消息
    * @Param: [req, resp]
    * @Return: void
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    private void reply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取消息的id
        Integer id = Integer.parseInt(req.getParameter("id"));
        // 实例化Message、User类，并设置需要的属性
        Message message = new Message();
        User user = new User();
        message.setId(id);
        // 获取id对应的消息信息
        Message result = SMSService.searchMessage(message);
        // 将消息中的fromId赋给user中的id
        user.setId(result.getFromId());
        // 根据这个id查找对应的用户信息
        List<User> users = SMSService.searchUserById(user);
        if (!users.isEmpty()){
            // 查询成功后放入作用域返回
            req.setAttribute("userList",users);
            req.getRequestDispatcher("sendMessage.jsp").forward(req,resp);
        }
    }

    /**
    * @Description: 阅读消息
    * @Param: [req, resp]
    * @Return: void
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    private void readMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取id参数
        Integer id = Integer.parseInt(req.getParameter("id"));
        // 实例化实体类并初始化Message
        Message message = new Message();
        if (id != null){
            message.setId(id);
            message.setStatus(Message.STATUS_READ);
            // 根据id值查询一条消息
            Message result = SMSService.searchMessage(message);
            SMSService.update(message);
            if (!result.getAttachment().isEmpty()){
                // 如果文件存在则获取文件名
                String fileName = result.getAttachment().substring(result.getAttachment().lastIndexOf("/") + 1);
                // 存到作用域中
                req.setAttribute("fileName",fileName);
            }else {
                // 如果文件存在则获取文件名
                String fileName = "";
                // 存到作用域中
                req.setAttribute("fileName",fileName);
            }
            // 根据查询结果中的fromId查询发送人的名字
            User user = SMSService.searchUsername(result);
            // 将查询后的信息放到作用域中
            req.setAttribute("user",user);
            req.setAttribute("message",result);
            req.getRequestDispatcher("readMessage.jsp").forward(req,resp);
        }
    }

    /**
    * @Description: 用户发送短消息
    * @Param: [req, resp]
    * @Return: void
    * @Author: 吴蒙召
    * @Date: 2019/6/8
    **/
    private void sendMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取表单的各项参数
        Integer toId = Integer.parseInt(req.getParameter("to_id"));
        String subject = req.getParameter("subject");
        String content = req.getParameter("content").trim();
        // 获取附件
        Part attachment = req.getPart("attachment");
        String filePath;
        if (!attachment.getSubmittedFileName().isEmpty()){
            filePath = writeFile(req,attachment);
        }else {
            filePath = "";
        }

        //获取session中用户的id，此即为发送者的id
        User us = (User) req.getSession().getAttribute("user");
        Integer fromId = us.getId();

        // 实例化Messages类并设置消息的各项属性
        Message message = new Message();
        message.setFromId(fromId);
        message.setToId(toId);
        message.setSubject(subject);
        message.setContent(content);
        message.setStatus(Message.STATUS_UNREAD);
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        message.setAttachment(filePath);

        // 调用service层的方法执行添加消息
        Object id = SMSService.insert(message);
        if (id != null){
            List<Message> messageList = SMSService.searchMessage(us);
            req.setAttribute("messageList",messageList);
            req.getRequestDispatcher("main.jsp").forward(req,resp);
        }
    }

    /**
    * @Description: 执行文件上传，并获取文件上传后的访问路径
    * @Param: [req, attachment]
    * @Return: java.lang.String
    * @Author: 吴蒙召
    * @Date: 2019/6/10
    **/
    private String writeFile(HttpServletRequest req, Part attachment) throws IOException {
        // 获取文件上传要保存到的文件地址
        String path = getServletContext().getRealPath("upload/");
        // 如果文件夹不存在则创建该文件夹
        File uploadDir = new File(path);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        // 获取文件的原始名字
        String fileName = attachment.getSubmittedFileName();
        // 获取文件的不带后缀的名称
        String baseName = FilenameUtils.getBaseName(fileName);
        // 获取文件的后缀
        String extName = FilenameUtils.getExtension(fileName);
        fileName = baseName + "_" + System.currentTimeMillis() + "." + extName;

        //创建文件
        File file = new File(path,fileName);
        attachment.write(file.getAbsolutePath());

        // 文件上传后的访问路径
        String basePath = req.getRequestURL().substring(0,req.getRequestURL().lastIndexOf("/"));
        return basePath + "/upload/" + fileName;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
            case "sendMessage":
                sendMessage(req,resp);
                break;
            default:
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().println("<script>alert('sorry,您的操作不合法！！！');window.history.back();</script>");
                break;
        }
    }

    /**
     * @Description: 用户注销系统
     * @Param: [req, resp]
     * @Return: void
     * @Author: 吴蒙召
     * @Date: 2019/6/8
     **/
    private void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 移除session中user属性
        req.getSession().removeAttribute("user");
        // 重定向到登录页
        resp.sendRedirect("login.jsp");
    }

    /**
     * @Description: 用户注册
     * @Param: [req, resp]
     * @Return: void
     * @Author: 吴蒙召
     * @Date: 2019/6/8
     **/
    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取前端传来参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String account = req.getParameter("account");
        String relPassword = req.getParameter("rel_password");
        String email = req.getParameter("email");

        // 对参数进行后台的校验
        if (!password.equals(relPassword)) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<script>alert('您的两次密码不一致');window.location.href='register.jsp'</script>");
            return;
        }
        // 后台对邮箱格式进行正则校验
//        if (email){

        // 实例化User类并将参数赋给实体类对应的属性
        User user = new User();
        user.setUsername(username);
        user.setAccount(account);
        user.setPassword(password);
        user.setEmail(email);
        user.setStatus(User.STATUS_NORMAL);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));

        // 交给service处理
        Object id = SMSService.insert(user);
        // 如果注册成功就免登录直接进入主界面
        if (id != null) {
            // 用户的信息放在session作用域中
            req.getSession().setAttribute("user", user);
            // 账号密码存在cookie里用于密码自动填充
            Cookie cookie = new Cookie(username, password);
            resp.addCookie(cookie);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }

    }

    /**
     * @Description: 用户登录
     * @Param: [req, resp]
     * @Return: void
     * @Author: 吴蒙召
     * @Date: 2019/6/8
     **/
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名和密码
        String account = req.getParameter("username");
        String password = req.getParameter("password");

        // 实例化User类并将参数赋给实体类中的对应的属性
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        // 通过数据库校验账号、密码
        List<User> userList = SMSService.search(user);
        // 查询结果不为空则登录成功，否则登录失败
        if (userList !=null && !userList.isEmpty()) {
            // 拿到查询到的用户的id
            int id = userList.get(0).getId();
            // 把用户id、登录时间设置上
            user.setId(id);
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            // 更新用户的登录时间
            int row = SMSService.update(user);
            // 更新成功则进行后续处理
            if (row != 0) {
                // 查找当前用户的消息
                List<Message> messageList = SMSService.searchMessage(user);
                // 用户的信息放在session作用域中
                req.getSession().setAttribute("user", user);
                // 用户的消息存放在request作用域中
                req.setAttribute("messageList", messageList);
                // 账号密码存在cookie里用于密码自动填充
                Cookie cookie = new Cookie(account, password);
                resp.addCookie(cookie);
                req.getRequestDispatcher("main.jsp").forward(req, resp);
            }
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<script>alert('账号或密码有误，请检查后再登录');window.location.href='login.jsp';</script>");
        }
    }

}
