package com.gemframework.controller;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.vo.UserRolesVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.UserRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Title: UserRolesController.java
 * @Package: com.gemframework.controller
 * @Date: 2019-12-05 22:22:33
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@RestController
@RequestMapping("userRoles")
public class UserRolesController {

    @Autowired
    UserRolesService userRolesService;

    /**
     * @Title:  add
     * @MethodName:  增
     * @Param: [vo, bindingResult]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:33
     */
    @PostMapping("add")
    public BaseResultData add(@Valid @RequestBody UserRolesVo vo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return BaseResultData.SUCCESS(userRolesService.save(vo));
    }

    /**
     * @Title:  delete
     * @MethodName:  删
     * @Param: [id]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:33
     */
    @PostMapping("delete/{id}")
    public BaseResultData delete(@PathVariable("id") Long id){
        userRolesService.delete(id);
        return BaseResultData.SUCCESS();
    }

    /**
     * @Title:  update
     * @MethodName:  改
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:33
     */
    @PostMapping("update")
    public BaseResultData update(UserRolesVo vo){
        return BaseResultData.SUCCESS(userRolesService.update(vo));
    }

    /**
     * @Title:  list
     * @MethodName:  查-list
     * @Param: []
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:33
     */
    @GetMapping("list")
    public BaseResultData list(){
        List list = userRolesService.findListAll();
        return BaseResultData.SUCCESS(list);
    }


    /**
     * @Title:  listByParams
     * @MethodName:  listByParams
     * @Param: [vo]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description:
     * @Date: 2019-12-05 22:22:33
     */
    @GetMapping("listByParams")
    public BaseResultData listByParams(UserRolesVo vo){
        List<UserRolesVo> list = userRolesService.findListByParams(vo);
        return BaseResultData.SUCCESS(list);
    }

    /**
     * @Title:  pageByParams
     * @MethodName:  pageByParams
     * @Param: [vo, pageable]
     * @Retrun: com.gemframework.model.BaseResult
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:22:33
     */
    @GetMapping("pageByParams")
    public BaseResultData pageByParams(UserRolesVo vo, Pageable pageable){
        PageInfo page =  userRolesService.findPageByParams(vo,pageable);
        return BaseResultData.SUCCESS(page);
    }

}
