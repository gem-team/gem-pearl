package com.gemframework.service;

import com.gemframework.model.vo.ModuleAttrVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModuleAttrService {

    boolean exist(ModuleAttrVo vo);

    ModuleAttrVo save(ModuleAttrVo vo);

    List<ModuleAttrVo> findListAll();

    List<ModuleAttrVo> findListByParams(ModuleAttrVo vo);

    PageInfo findPageByParams(ModuleAttrVo vo, Pageable pageable);

    void delete(Long id);

    void deleteBatch(List<ModuleAttrVo> vos);

    ModuleAttrVo getById(Long id);
}
