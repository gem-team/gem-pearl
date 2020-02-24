package com.gemframework.service.impl;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.Dept;
import com.gemframework.model.po.Menu;
import com.gemframework.model.vo.DeptVo;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.DeptRepository;
import com.gemframework.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptRepository deptRepository;

    @Override
    public boolean exist(DeptVo vo) {
        if(null == deptRepository.exist(vo.getName(),vo.getId())){
            return true;
        }
        return false;
    }

    /**
     * @Title:  add
     * @MethodName:  add
     * @Param: [vo]
     * @Retrun: com.gemframework.model.po.Dept
     * @Description: 添加
     * @Date: 2019-12-05 22:07:32
     */
    @Override
    public DeptVo save(DeptVo vo) {
        if(!exist(vo)){
            throw new GemException(ResultCode.DEPT_EXIST);
        }
        Dept dept = new Dept();
        GemBeanUtils.copyProperties(vo,dept);
        dept = deptRepository.saveAndFlush(dept);

        //更新id_path,series开始
        DeptVo parentVo = getById(vo.getPid());
        String idPath = String.valueOf(dept.getId());
        if(dept.getId()<10){
            idPath = "0"+dept.getId();
        }
        if(parentVo != null && parentVo.getIdPath() != null){
            idPath = parentVo.getIdPath()+"-"+idPath;
        }
        //设置idpath
        dept.setIdPath(idPath);

        //设置series
        String series = dept.getIdPath();
        if(series.indexOf("-")>0){
            series = series.substring(0,series.indexOf("-"));
        }
        dept.setSeries(series);
        //更新
        dept = deptRepository.save(dept);
        //更新id_path,series结束

        GemBeanUtils.copyProperties(dept,vo);
        return vo;
    }

    /**
     * @Title:  findListAll
     * @MethodName:  findListAll
     * @Param: []
     * @Retrun: java.util.List
     * @Description:  查询所有数据列表
     * @Date: 2019-12-05 22:07:32
     */
    @Override
    public List<DeptVo> findListAll() {
        List<Dept> list = deptRepository.findAll();
        List<DeptVo> vos = GemBeanUtils.copyCollections(list,DeptVo.class);
        return vos;
    }

    /**
     * @Title:  findListByParams
     * @MethodName:  findListByParams
     * @Param: [vo]
     * @Retrun: java.util.List
     * @Description: 动态查询数据
     * //创建匹配器，即使用查询条件
     * @Date: 2019-12-05 22:07:32
     */
    @Override
    public List<DeptVo> findListByParams(DeptVo vo) {
        Dept dept = new Dept();
        GemBeanUtils.copyProperties(vo,dept);
        Example<Dept> example =Example.of(dept);
        List<Dept> list = deptRepository.findAll(example);
        List<DeptVo> vos = GemBeanUtils.copyCollections(list,DeptVo.class);
        return vos;
    }


    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:07:32
     */
    @Override
    public PageInfo findPageByParams(DeptVo vo, Pageable pageable) {
        Dept dept = new Dept();
        GemBeanUtils.copyProperties(vo,dept);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        Example<Dept> example =Example.of(dept,matcher);
        Page<Dept> page = deptRepository.findAll(example,pageable);
        PageInfo pageInfo = PageInfo.builder()
                .total(page.getTotalElements())
                .rows(page.getContent())
                .build();
        return pageInfo;
    }

    /**
     * @Title:  delete
     * @MethodName:  delete
     * @Param: [id]
     * @Retrun: void
     * @Description: 根据ID删除数据
     * @Date: 2019-12-05 22:07:32
     */
    @Override
    public void delete(Long id) {
        if(!deptRepository.existsById(id)){
            throw new GemException(ResultCode.DATA_NOT_EXIST);
        }
        deptRepository.deleteById(id);

    }

    /**
     * @Title: 根据ID获取对象
     * @Param: id
     * @Retrun: Entity
     * @Description:
     * @Date: 2019/12/5 22:40
     */
    @Override
    public DeptVo getById(Long id) {
        DeptVo vo = new DeptVo();
        Dept entity = deptRepository.getById(id);

        if(entity!=null){
            GemBeanUtils.copyProperties(entity,vo);
            if(vo.getPid()!=null){
                vo.setPname("公司总部");
                //父级信息
                entity = deptRepository.getById(vo.getPid());
                if(entity!=null){
                    vo.setPname(entity.getName());
                }
            }
        }
        return vo;
    }
}
