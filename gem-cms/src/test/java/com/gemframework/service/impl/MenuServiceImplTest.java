package com.gemframework.service.impl;

import com.gemframework.common.utils.GemBeanUtils;
import com.gemframework.model.po.Menu;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: MenuServiceImplTest.java
 * @Package: com.gemframework.service.impl
 * @Date: 2019/12/20 20:50
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MenuServiceImplTest {
    @Resource
    private MenuRepository menuRepository;

    @Test
    void findListAll() {
        List<Menu> list = menuRepository.findAll();
        List<MenuVo> vos = GemBeanUtils.copyCollections(list,MenuVo.class);
        for(MenuVo vo :vos){
            if(vo.getIdPath().lastIndexOf("-")>0){
                vo.setParentIdPath(vo.getIdPath().substring(0,vo.getIdPath().lastIndexOf("-")));
            }
        }
    }
}
