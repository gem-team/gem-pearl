package com.gemframework.controller;

import org.apache.commons.io.IOUtils;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: IndexController.java
 * @Package: com.gemframework.gembasic.controller
 * @Date: 2019/11/28 18:03
 * @Version: v1.0
 * @Description: 反向生成代码控制器
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Controller
@RequestMapping("code")
public class GenerateController {

    @Resource
    GenerateService generateService;


    /**
     * 数据库表页面
     * @param request
     * @param model
     * @return
     */
    @GetMapping("list.html")
    public String list(HttpServletRequest request, Model model){
        return "code/list";
    }


    /**
     * 数据库表页面
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("page")
    @ResponseBody
    public BaseResultData pageByParams(@RequestParam(value = "name",required = false) String name,
                                       @RequestParam(value = "page",defaultValue = "0") int page,
                                       @RequestParam(value = "size",defaultValue = "10") int size){
        PageInfo pageInfo =  generateService.findTablesPageByParams(name,page,size);
        return BaseResultData.SUCCESS(pageInfo);
    }


    /**
     * 代码反向生成
     * @param tables
     * @param response
     * @throws IOException
     */
    @RequestMapping("/generate")
    public void generate(String[] tables, HttpServletResponse response) throws IOException {
        byte[] data = generateService.generatorCode(tables);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"gemcode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
