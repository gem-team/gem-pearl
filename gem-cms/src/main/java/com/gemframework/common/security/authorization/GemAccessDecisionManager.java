package com.gemframework.common.security.authorization;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import com.gemframework.common.security.exception.GemAccessDeniedException;
import com.gemframework.common.security.exception.GemAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Title: 权限校验
 * @Package: com.gemframework.common.security.scheme
 * @Date: 2019/12/11 15:23
 * @Version: v1.0
 * @Description: 这里写描述
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Slf4j
@Component
public class GemAccessDecisionManager implements AccessDecisionManager {

    /**
     * @Title: 判定是否拥有权限的决策方法
     * @Param: [
     *  authentication: 登录时UserService中循环添加到 GrantedAuthority 对象中的权限信息集合
     *  object: 包含客户端发起的请求的requset信息,可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest()
     *  configAttributes: GemMetadataSourceService初始化的值，getAttributes(Object object)
     * 这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在权限表中则拒绝。
     * ]
     * @Retrun: void
     * @Description:
     * @Date: 2019/12/8 22:31
     */
    @Override
    public void decide(Authentication authentication,
                       Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (null == configAttributes || configAttributes.size() <= 0) {
            return;
        }
        ConfigAttribute configAttribute;
        String needRole;
        for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            configAttribute = iter.next();
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限 抛出拒绝访问异常
        throw new GemAccessDeniedException(ResultCode.PERMISSION_DENIED, ResultURL.REFUSE);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
