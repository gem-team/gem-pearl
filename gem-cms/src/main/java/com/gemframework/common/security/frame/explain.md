####AccessDecisionManager 权限校验
####FilterInvocationSecurityMetadataSource 权限配置数据库加载
####AbstractSecurityInterceptor  Spring security 核心抽象接口
####AuthenticationManager  自定义用户角色数据
####WebSecurityConfigurerAdapter Spring security核心配置

https://www.cnblogs.com/zhongzheng123/p/9199205.html



    //1.从HttpServletRequest中获取SecurityContextImpl对象
    SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute(SESSION_USERKEY);
    //2.从SecurityContextImpl中获取Authentication对象
    Authentication authentication = securityContextImpl.getAuthentication();
    //3.初始化UsernamePasswordAuthenticationToken实例 ，这里的参数user就是我们要更新的用户信息
    //UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials());
    //auth.setDetails(authentication.getDetails());
    //4.重新设置SecurityContextImpl对象的Authentication
    //securityContextImpl.setAuthentication(auth);
