package com.gemframework.controller;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.RoleService;
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
 * @Title: RoleController.java
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
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

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
    public BaseResultData add(@Valid @RequestBody RoleVo vo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return BaseResultData.SUCCESS(roleService.save(vo));
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
    public BaseResultData delete(Long id){
        roleService.delete(id);
        return BaseResultData.SUCCESS();
    }

    /**
     * @Title:  deleteAll
     * @MethodName:  删-全部
     * @Param: [id]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:18
     */
    @PostMapping("deleteBatch")
    @ResponseBody
    public BaseResultData deleteBatch(@RequestBody List<UserVo> vos){
        roleService.deleteBatch(vos);
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
    @ResponseBody
    public BaseResultData list(){
        List list = roleService.findListAll();
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
    @ResponseBody
    public BaseResultData listByParams(RoleVo vo){
        List<RoleVo> list = roleService.findListByParams(vo);
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
    @ResponseBody
    public BaseResultData pageByParams(RoleVo vo, Pageable pageable){
        PageInfo page =  roleService.findPageByParams(vo,pageable);
        return BaseResultData.SUCCESS(page);
    }


    @GetMapping("list.html")
    public String list(Model model){
        return "role/list";
    }

    @GetMapping("add.html")
    public String add(Model model){
        return "role/add";
    }

    @GetMapping("edit.html")
    public String edit(Model model, Long id){
        RoleVo roleVo = roleService.getById(id);
        model.addAttribute("edit_role",roleVo);
        return "role/edit";
    }
}
