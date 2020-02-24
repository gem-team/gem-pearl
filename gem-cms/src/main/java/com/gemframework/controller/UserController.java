package com.gemframework.controller;

import com.gemframework.model.po.User;
import com.gemframework.model.vo.UserVo;
import com.gemframework.model.vo.request.ResetPasswordReq;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.UserService;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.model.BaseResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Title: UserController.java
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
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @Title:  add
     * @MethodName:  增
     * @Param: [vo, bindingResult]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:17
     */
    @PostMapping("add")
    @ResponseBody
    public BaseResultData add(@Valid @RequestBody UserVo vo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        vo = userService.save(vo);
        return BaseResultData.SUCCESS(vo);
    }

    /**
     * @Title:  delete
     * @MethodName:  删
     * @Param: [id]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:18
     */
    @PostMapping("delete/{id}")
    @ResponseBody
    public BaseResultData delete(@PathVariable("id") Long id){
        userService.delete(id);
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
        userService.deleteBatch(vos);
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
    @PostMapping("deleteAll")
    @ResponseBody
    public BaseResultData deleteAll(){
        userService.deleteAll();
        return BaseResultData.SUCCESS();
    }

    /**
     * @Title:  update
     * @MethodName:  改
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:17
     */
    @PostMapping("edit")
    @ResponseBody
    public BaseResultData edit(@Valid @RequestBody UserVo vo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return BaseResultData.SUCCESS(userService.save(vo));
    }

    /**
     * @Title:  resetPassword
     * @MethodName:  修改密码
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:17
     */
    @PostMapping("resetPassword")
    @ResponseBody
    public BaseResultData resetPassword(@RequestBody ResetPasswordReq req){
        UserVo vo = new UserVo();
        vo.setId(req.getId());
        vo.setPassword(req.getNewPass());
        return BaseResultData.SUCCESS(userService.save(vo));
    }

    /**
     * @Title:  查询单个
     * @MethodName:  查询
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:32
     */
    @GetMapping("getOne")
    @ResponseBody
    public BaseResultData get(Long id){
        return BaseResultData.SUCCESS(userService.getById(id));
    }


    /**
     * @Title:  list
     * @MethodName:  查-list
     * @Param: []
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 16:18
     */
    @GetMapping("list")
    @ResponseBody
    public BaseResultData list(){
        List list = userService.findListAll();
        return BaseResultData.SUCCESS(list);
    }

    /**
     * @Title:  listByParams
     * @MethodName:  listByParams
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019/11/29 21:19
     */
    @GetMapping("listByParams")
    @ResponseBody
    public BaseResultData listByParams(UserVo vo){
        List list = userService.findListByParams(vo);
        return BaseResultData.SUCCESS(null);
    }

    /**
     * @Title:  pageByParams
     * @MethodName:  pageByParams
     * @Param: [vo, pageable]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019/11/29 21:21
     */
    @GetMapping("pageByParams")
    @ResponseBody
    public BaseResultData pageByParams(UserVo vo, Pageable pageable){
        PageInfo page =  userService.findPageByParams(vo,pageable);
        return BaseResultData.SUCCESS(page);

    }


    @GetMapping("list.html")
    public String list(Model model){
        return "user/list";
    }

    @GetMapping("add.html")
    public String add(Model model){
        return "user/add";
    }

    @GetMapping("edit.html")
    public String edit(Model model, Long id){
        UserVo userVo = userService.getById(id);
        model.addAttribute("edit_user",userVo);
        return "user/edit";
    }

}
