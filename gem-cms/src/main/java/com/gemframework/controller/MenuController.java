package com.gemframework.controller;

import com.alibaba.fastjson.JSON;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.security.authorization.GemMetadataSourceService;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.po.Menu;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Title: MenuController.java
 * @Package: com.gemframework.controller
 * @Date: 2019-12-05 22:22:32
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    GemMetadataSourceService gemMetadataSourceService;

    /**
     * @Title:  add
     * @MethodName:  增
     * @Param: [vo, bindingResult]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:32
     */
    @PostMapping("add")
    @ResponseBody
    public BaseResultData add(@Valid @RequestBody MenuVo vo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return BaseResultData.SUCCESS(menuService.save(vo));
    }

    /**
     * @Title:  delete
     * @MethodName:  删
     * @Param: [id]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:32
     */
    @PostMapping("delete")
    @ResponseBody
    public BaseResultData delete(@RequestParam(value = "id") Long id){
        menuService.delete(id);
        return BaseResultData.SUCCESS();
    }


    /**
     * @Title:  list
     * @MethodName:  查-list
     * @Param: []
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:32
     */
    @GetMapping("list")
    public BaseResultData list(){
        List<MenuVo> list = menuService.findListAll();
        return BaseResultData.SUCCESS(list);
    }


    /**
     * @Title:  listByParams
     * @MethodName:  listByParams
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:32
     */
    @GetMapping("listByParams")
    public BaseResultData listByParams(MenuVo vo){
        List<MenuVo> list = menuService.findListByParams(vo);
        return BaseResultData.SUCCESS(list);
    }

    /**
     * @Title:  pageByParams
     * @MethodName:  pageByParams
     * @Param: [vo, pageable]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:22:32
     */
    @GetMapping("pageByParams")
    public BaseResultData pageByParams(MenuVo vo, Pageable pageable){
        PageInfo page =  menuService.findPageByParams(vo,pageable);
        return BaseResultData.SUCCESS(page);
    }


    @GetMapping("list.html")

    public String list(Model model){
        List<MenuVo> list = menuService.findListAll();
        model.addAttribute("list_menus",list);
        return "menu/list";
    }

    @GetMapping("add.html")
    public String addHtml(){
        return "menu/add";
    }

    @GetMapping("addChild.html")
    public String addChildHtml(Model model, Long id){
        MenuVo menuVo = menuService.getById(id);
        model.addAttribute("parent_menu",menuVo);
        return "menu/addChild";
    }

    @GetMapping("edit.html")
    public String editHtml(Model model, Long id){
        MenuVo menuVo = menuService.getById(id);
        model.addAttribute("edit_menu",menuVo);
        return "menu/edit";
    }

}
