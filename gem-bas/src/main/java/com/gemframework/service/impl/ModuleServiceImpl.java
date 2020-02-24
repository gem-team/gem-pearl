package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.WhetherEnum;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.Module;
import com.gemframework.model.po.ModuleAttr;
import com.gemframework.model.vo.ModuleAttrVo;
import com.gemframework.model.vo.ModuleVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.ModuleAttrRepository;
import com.gemframework.repository.ModuleRepository;
import com.gemframework.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleRepository moduleRepository;
    @Resource
    private ModuleAttrRepository moduleAttrRepository;

    @Override
    public boolean exist(ModuleVo vo) {
        List<Module> list = moduleRepository.exist(vo.getNameEn(),vo.getId());
        if(vo.getId() == null){
            list = moduleRepository.exist(vo.getNameEn(),0L);
        }
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public ModuleVo save(ModuleVo vo) {
        if(exist(vo)){
            throw new GemException(ResultCode.MODULE_EXIST);
        }
        Module entity = new Module();
        GemBeanUtils.copyProperties(vo,entity);
        entity = moduleRepository.save(entity);

        //保存成功后，默认插入自增ID属性
        ModuleAttrVo moduleAttrVo = ModuleAttrVo.builder()
                .moduleId(entity.getId())
                .attrName("id")
                .attrSort(1)
                .comment("自增ID")
                .attrType("hidden")
                .minLength(1)
                .maxLength(20)
                .editType("text")
                .isNull(0)
                .isSearch(0)
                .isVisit(0)
                .isSort(0)
                .build();
        List<ModuleAttrVo> attrVos = vo.getModuleAttrs();
        if(vo.getId() == null){
            attrVos.add(moduleAttrVo);
        }
        List<ModuleAttr> list = GemBeanUtils.copyCollections(attrVos,ModuleAttr.class);
        for(ModuleAttr attr:list){
            attr.setModuleId(entity.getId());
        }
        moduleAttrRepository.saveAll(list);
        GemBeanUtils.copyProperties(entity,vo);
        return vo;
    }

    @Override
    public void updateIsGenerate(WhetherEnum whetherEnum,Long id) {
        moduleRepository.updateIsGenerate(whetherEnum.getCode(),id);
    }

    @Override
    public List<ModuleVo> findListAll() {
        List<Module> list = moduleRepository.findAll();
        List<ModuleVo> vos = GemBeanUtils.copyCollections(list,ModuleVo.class);
        return vos;
    }

    @Override
    public List<ModuleVo> findListByParams(ModuleVo vo) {
        Module entity = new Module();
        GemBeanUtils.copyProperties(vo,entity);
        List<Module> list = moduleRepository.findAll(Example.of(entity));
        List<ModuleVo> vos = GemBeanUtils.copyCollections(list,ModuleVo.class);
        return vos;
    }

    @Override
    public PageInfo findPageByParams(ModuleVo vo, Pageable pageable) {
        Module entity = new Module();
        GemBeanUtils.copyProperties(vo,entity);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Page<Module> page = moduleRepository.findAll(Example.of(entity,matcher),pageable);
        List<ModuleVo> vos = GemBeanUtils.copyCollections(page.getContent(),ModuleVo.class);
        if(vos!=null && vos.size()>0){
            for(ModuleVo moduleVo:vos){
                setModuleAttrs(moduleVo);
            }
        }
        PageInfo pageInfo = PageInfo.builder()
                .total(page.getTotalElements())
                .rows(vos)
                .build();
        return pageInfo;
    }

    @Override
    public void delete(Long id) {
        if(!moduleRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        moduleRepository.deleteById(id);

        //对应删除模块下的属性
        List<ModuleAttr> list = moduleAttrRepository.findByModuleId(id);
        moduleAttrRepository.deleteInBatch(list);
    }

    @Override
    public void deleteBatch(List<ModuleVo> vos) {
        List<Module> list = GemBeanUtils.copyCollections(vos,Module.class);
        moduleRepository.deleteInBatch(list);
        for(Module module:list){
            //对应删除模块下的属性
            List<ModuleAttr> moduleAttrs = moduleAttrRepository.findByModuleId(module.getId());
            moduleAttrRepository.deleteInBatch(moduleAttrs);
        }
    }

    @Override
    public ModuleVo getById(Long id) {
        ModuleVo vo  = new ModuleVo();
        Module entity = moduleRepository.getById(id);
        GemBeanUtils.copyProperties(entity,vo);
        setModuleAttrs(vo);
        return vo;
    }


    private void setModuleAttrs(ModuleVo vo){
        List<ModuleAttr> moduleAttrs = moduleAttrRepository.findByModuleId(vo.getId());
        if(moduleAttrs!=null && moduleAttrs.size()>0){
            List<ModuleAttrVo> moduleAttrVos = new ArrayList<>();
            for(ModuleAttr moduleAttr:moduleAttrs){
                ModuleAttrVo moduleAttrVo = new ModuleAttrVo();
                GemBeanUtils.copyProperties(moduleAttr,moduleAttrVo);
                moduleAttrVos.add(moduleAttrVo);
            }
            vo.setModuleAttrs(moduleAttrVos);
        }
    }
}

