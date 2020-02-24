package com.gemframework.controller;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.vo.ModuleAttrVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.ModuleAttrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * @Title: ModuleAttrController.java
 * @Package: com.gemframework.controller
 * @Date: 2020-01-29 18:16:21
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Controller
@RequestMapping("moduleAttr")
public class ModuleAttrController {

    @Resource
    ModuleAttrService moduleAttrService;

    @GetMapping("list.html")
    public String list(HttpServletRequest request,Model model){
        String moduleId = request.getParameter("moduleId");
        model.addAttribute("moduleId",moduleId);
        return "moduleAttr/list";
    }

    @GetMapping("pageByParams")
    @ResponseBody
    public BaseResultData pageByParams(ModuleAttrVo vo, Pageable pageable){
        PageInfo info =  moduleAttrService.findPageByParams(vo,pageable);
        return BaseResultData.SUCCESS(info);
    }

    @GetMapping("add.html")
    public String add(HttpServletRequest request,Model model){
        String moduleId = request.getParameter("moduleId");
        model.addAttribute("moduleId",moduleId);
        return "moduleAttr/add";
    }


    @GetMapping("edit.html")
    public String edit(Model model, Long id){
        ModuleAttrVo vo = moduleAttrService.getById(id);
        model.addAttribute("editInfo",vo);
        return "moduleAttr/edit";
    }

    @PostMapping("add")
    @ResponseBody
    public BaseResultData add(@Valid @RequestBody ModuleAttrVo vo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return BaseResultData.SUCCESS(moduleAttrService.save(vo));
    }

    @PostMapping("delete")
    @ResponseBody
    public BaseResultData delete(Long id){
        moduleAttrService.delete(id);
        return BaseResultData.SUCCESS();
    }

    @PostMapping("deleteBatch")
    @ResponseBody
    public BaseResultData deleteBatch(@RequestBody List<ModuleAttrVo> vos){
        moduleAttrService.deleteBatch(vos);
        return BaseResultData.SUCCESS();
    }

    @GetMapping("getOne")
    @ResponseBody
    public BaseResultData get(Long id){
        return BaseResultData.SUCCESS(moduleAttrService.getById(id));
    }



}
