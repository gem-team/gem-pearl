package com.gemframework.controller;

import com.gemframework.common.config.GemSystemProperties;
import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.WhetherEnum;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.common.utils.GemFilesUtils;
import com.gemframework.common.utils.GemGenerate;
import com.gemframework.model.BaseResultData;
import com.gemframework.model.vo.ModuleVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;


/**
 * @Title: ModuleController.java
 * @Package: com.gemframework.controller
 * @Date: 2020-01-28 20:11:39
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangys
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Controller
@RequestMapping("module")
public class ModuleController {

    @Resource
    private GemSystemProperties gemSystemProperties;

    @Resource
    private ModuleService moduleService;

    @GetMapping("list.html")
    public String list(Model model) {
        return "module/list";
    }

    @GetMapping("pageByParams")
    @ResponseBody
    public BaseResultData pageByParams(ModuleVo vo, Pageable pageable) {
        PageInfo page = moduleService.findPageByParams(vo, pageable);
        for(ModuleVo moduleVo:(List<ModuleVo>)page.getRows()){
            String downLoadPath = gemSystemProperties.getGenerateCodeServerPath()+"/code_"+moduleVo.getId()+".zip";
            log.info("文件路径："+downLoadPath);
            File file = new File(downLoadPath);
            if(!file.exists()){
                // 文件不存在
                log.info("文件不存在："+moduleVo.getId()+":"+WhetherEnum.NO);
                moduleService.updateIsGenerate(WhetherEnum.NO,moduleVo.getId());
            }
        }
        return BaseResultData.SUCCESS(page);
    }

    @GetMapping("list")
    @ResponseBody
    public BaseResultData list(HttpServletRequest request) {
        List<ModuleVo> list = moduleService.findListAll();
        String moduleId = request.getParameter("moduleId");
        if (moduleId != null
                && !moduleId.equalsIgnoreCase("")
                && !moduleId.equalsIgnoreCase("null")) {
            ModuleVo vo = new ModuleVo();
            vo.setId(Long.valueOf(moduleId));
            list = moduleService.findListByParams(vo);
        }
        return BaseResultData.SUCCESS(list);
    }

    @GetMapping("add.html")
    public String add(Model model) {
        return "module/add";
    }


    @GetMapping("edit.html")
    public String edit(Model model, Long id) {
        ModuleVo vo = moduleService.getById(id);
        model.addAttribute("editInfo", vo);
        return "module/edit";
    }

    @PostMapping("add")
    @ResponseBody
    public BaseResultData add(@Valid @RequestBody ModuleVo vo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResultData.ERROR(ResultCode.PARAM_EXCEPTION.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        return BaseResultData.SUCCESS(moduleService.save(vo));
    }

    @PostMapping("delete")
    @ResponseBody
    public BaseResultData delete(Long id) {
        moduleService.delete(id);
        return BaseResultData.SUCCESS();
    }

    @PostMapping("deleteBatch")
    @ResponseBody
    public BaseResultData deleteBatch(@RequestBody List<ModuleVo> vos) {
        moduleService.deleteBatch(vos);
        return BaseResultData.SUCCESS();
    }

    @GetMapping("getOne")
    @ResponseBody
    public BaseResultData get(Long id) {
        return BaseResultData.SUCCESS(moduleService.getById(id));
    }

    @PostMapping("generateCode")
    @ResponseBody
    public BaseResultData generate(@RequestBody ModuleVo vo) {
        if (vo.getModuleAttrs() == null || vo.getModuleAttrs().size() == 0) {
            throw new GemException(ResultCode.MODULE_ATTR_ERROR);
        }

        List<GemGenerate.TempParas.Filed> list = GemBeanUtils.copyCollections(vo.getModuleAttrs(),
                GemGenerate.TempParas.Filed.class);
        GemGenerate.TempParas tempParas = GemGenerate.TempParas.builder()
                .id(vo.getId())
                .packageName(vo.getPackageName())
                .entityEn(vo.getNameEn())
                .entityCN(vo.getNameCn())
                .pkName(vo.getPkNane())

                .isAdd(vo.getIsAdd() == 1 ? true : false)
                .isEdit(vo.getIsEdit() == 1 ? true : false)
                .isDel(vo.getIsDel() == 1 ? true : false)
                .isQuery(vo.getIsQuery() == 1 ? true : false)

                .pageWidth(vo.getPageWidth())
                .pageHeight(vo.getPageHeight())

                .fileds(list)
                .author(vo.getAuthor())
                .build();
        GemGenerate GemGenerate = new GemGenerate();
        boolean result = GemGenerate.generateCode(tempParas, gemSystemProperties.getGenerateCodeServerPath());
        if(result){
            moduleService.updateIsGenerate(WhetherEnum.YES,vo.getId());
        }
        return BaseResultData.SUCCESS();
    }


    @GetMapping("downloadCode")
    public void downloadCode(HttpServletResponse response,Long id) {
        String downLoadPath = gemSystemProperties.getGenerateCodeServerPath()+"/code_"+id+".zip";
        GemFilesUtils.download(response,downLoadPath, GemConstant.Character.UTF8);
    }
}