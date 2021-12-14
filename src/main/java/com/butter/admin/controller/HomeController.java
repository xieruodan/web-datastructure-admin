package com.butter.admin.controller;

import com.butter.admin.bean.User;
import com.butter.admin.bean.UserRegis;
import com.butter.admin.mapper.UserMapper;
import com.butter.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 去home页面
     * @return
     */
    @GetMapping(value = {"/"})
    public String homePage(HttpSession session) {
        log.info(String.valueOf(session.getAttribute("loginUser")));
        log.info("有人访问DataStructure.learner");
        return "home";
    }

    @GetMapping("/userlogin")
    public String loginPage() {
        log.info("有人访问登陆页面");
        return "login";
    }

//    @PostMapping("/userlogin")
//    public String loginPageSubmit(User user, HttpSession session, Model model) {
//        User currentUser = userService.getByUserName(user.getUserName());
//        if (currentUser.getPassword().equals(user.getPassword())) {
//            session.setAttribute("loginUser", currentUser);
//            log.info(currentUser.getPassword());
//            return "redirect:/home";
//        } else {
//            model.addAttribute("msg", "您记错账号或密码了");
//            return "login";
//        }
//    }

    @GetMapping("/register")
    public String registerPage() {
        log.info("有人访问注册页面");
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(UserRegis userRegis, Model model) {
        if (userRegis.getPassword1().equals(userRegis.getPassword2())) {
            User newUser = new User();
            newUser.setUserName(userRegis.getUserName());
            newUser.setPassword(userRegis.getPassword1());
            newUser.setEmail(userRegis.getEmail());
            newUser.setRoles("USER");
            userService.saveUser(newUser);
            model.addAttribute("msg", "注册成功，请登录");
            return "login";
        } else {
            model.addAttribute("msg", "两遍密码不一致，请重新输入");
            return "register";
        }
    }

    @GetMapping("/home")
    public String homePage2(HttpSession session) {
        log.info("有人访问DataStructure.learner");
        log.info(String.valueOf(session.getAttribute("loginUser")));
        return "home";
    }

    @GetMapping("/testPhoto")
    public String testPhoto() {
        return "testPhoto";
    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.setAttribute("loginUser", null);
//        return "home";
//    }
}