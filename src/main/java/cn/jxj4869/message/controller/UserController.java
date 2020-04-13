package cn.jxj4869.message.controller;


import cn.jxj4869.message.entity.User;
import cn.jxj4869.message.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;


    @PostMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(String email, HttpServletRequest request) throws JSONException {
        System.out.println("hh" + email);

        String code = userService.generatorCheckCode(email);
        request.getSession().setAttribute("checkCode", code);

        JSONObject result = new JSONObject();
        result.put("flag", true);
        return result.toString();

    }


    @PostMapping("/checkCheckCode")
    @ResponseBody
    public String checkCheckCode(String checkCode, HttpSession session) throws JSONException {
        System.out.println("hh");
        String checkCode1 = (String) session.getAttribute("checkCode");
        boolean flag = false;
        if (checkCode != null && checkCode1 != null && checkCode.equals(checkCode1)) {
            flag = true;
        }
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();

    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUsername(String userName) throws JSONException {
        boolean flag = userService.findByUserName(userName);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email) throws JSONException {
        boolean flag = userService.findByEmail(email);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }


    @PostMapping("/register")
    @ResponseBody
    public String register(User user) throws JSONException {

        System.out.println(user);
        boolean flag = userService.register(user);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "@ResponseBody");
        result.put("flag", true);
        return result.toString();
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(String userName, String password, HttpSession session) throws JSONException {
        User user = userService.login(userName, password);
        JSONObject result = new JSONObject();

        if (user!=null) {
            session.setAttribute("user",user);
            result.put("flag", true);

        }else{
            result.put("flag", false);
        }

        return result.toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/home";
    }


}
