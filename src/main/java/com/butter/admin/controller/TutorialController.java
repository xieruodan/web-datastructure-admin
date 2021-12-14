package com.butter.admin.controller;

import com.butter.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 9种算法的tutorial页面处理逻辑
 */
@Slf4j
@Controller
public class TutorialController {

    @GetMapping("/tutorial-sorting")
    public String sortingPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/sorting页面");
        } else {
            log.info("有一个游客访问了tutorial/sorting页面");
        }
        return "tutorial/sorting";
    }

    @GetMapping("/tutorial-linked list")
    public String linkedListPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/linked list页面");
        } else {
            log.info("有一个游客访问了tutorial/linked list页面");
        }
        return "tutorial/linked list";
    }

    @GetMapping("/tutorial-hash table")
    public String hashTablePage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/hash table页面");
        } else {
            log.info("有一个游客访问了tutorial/hash table页面");
        }
        return "tutorial/hash table";
    }

    @GetMapping("/tutorial-binary search tree")
    public String binarySearchTreePage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/binary search tree页面");
        } else {
            log.info("有一个游客访问了tutorial/binary search tree页面");
        }
        return "tutorial/binary search tree";
    }

    @GetMapping("/tutorial-graph traversal")
    public String graphTraversalPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/graph traversal页面");
        } else {
            log.info("有一个游客访问了tutorial/graph traversal页面");
        }
        return "tutorial/graph traversal";
    }

    @GetMapping("/tutorial-minimum spanning tree")
    public String minimumSpanningTreePage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/minimum spanning tree页面");
        } else {
            log.info("有一个游客访问了tutorial/minimum spanning tree页面");
        }
        return "tutorial/minimum spanning tree";
    }

    @GetMapping("/tutorial-shortest path")
    public String shortestPathPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/shortest path页面");
        } else {
            log.info("有一个游客访问了tutorial/shortest path页面");
        }
        return "tutorial/shortest path";
    }

    @GetMapping("/tutorial-cycle finding")
    public String cycleFindingPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/cycle finding页面");
        } else {
            log.info("有一个游客访问了tutorial/cycle finding页面");
        }
        return "tutorial/cycle finding";
    }

    @GetMapping("/tutorial-basic concept")
    public String basicConceptPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了tutorial/basic concept页面");
        } else {
            log.info("有一个游客访问了tutorial/basic concept页面");
        }
        return "tutorial/basic concept";
    }
}
