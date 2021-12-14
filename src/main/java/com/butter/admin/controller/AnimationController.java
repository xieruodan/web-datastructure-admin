package com.butter.admin.controller;

import com.butter.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 动画展示算法板块，设置跳转页
 */
@Slf4j
@Controller
public class AnimationController {


    @GetMapping("/animation/sorting")
    public String animationSorting (HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了animation/sorting页面");
        } else {
            log.info("有一个游客访问了animation/sorting页面");
        }
        return "animation/sorting";
    }

    @GetMapping("/animation/linkedList")
    public String animationLinkedList (HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了animation/linkedList页面");
        } else {
            log.info("有一个游客访问了animation/linkedList页面");
        }
        return "animation/linkedList";
    }

    @GetMapping("/animation/hashtable")
    public String animationHashtable (HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了animation/hashtable页面");
        } else {
            log.info("有一个游客访问了animation/hashtable页面");
        }
        return "animation/hashtable";
    }

    @GetMapping("/animation/graphTraversal")
    public String animationGraphTraversal (HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了animation/graphTraversal页面");
        } else {
            log.info("有一个游客访问了animation/graphTraversal页面");
        }
        return "animation/graphTraversal";
    }

}
