package com.gemframework.service;

import com.gemframework.common.enums.WhetherEnum;
import com.gemframework.model.vo.ModuleVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModuleService {

    boolean exist(ModuleVo vo);

    ModuleVo save(ModuleVo vo);

    void updateIsGenerate(WhetherEnum whetherEnum, Long id);

    List<ModuleVo> findListAll();

    List<ModuleVo> findListByParams(ModuleVo vo);

    PageInfo findPageByParams(ModuleVo vo, Pageable pageable);

    void delete(Long id);

    void deleteBatch(List<ModuleVo> vos);

    ModuleVo getById(Long id);
}
