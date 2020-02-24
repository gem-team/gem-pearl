package com.gemframework.controller;

import com.gemframework.common.security.authorization.GemMetadataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Title: LoginController.java
 * @Package: com.gemframework.gembasic.controller
 * @Date: 2019/11/28 18:03
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Controller
public class LoginController {

    @Autowired
    GemMetadataSourceService gemMetadataSourceService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("code","999");
        model.addAttribute("msg","系统错误");
        return "common/error";
    }

    @GetMapping("/403")
    public String denied(){
        return "common/refuse";
    }

    @GetMapping("/404")
    public String notFound(){
        gemMetadataSourceService.loadResourceDefine();
        return "common/notfind";
    }

    @GetMapping({"/index"})
    public String index(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if(principal == null || principal.getName() == null){
            return "login";
        }
        return "index";
    }

    @GetMapping({"/boxed"})
    public String layout(){
        return "layout/boxed";
    }
}
