package com.gemframework.common.response;

import com.alibaba.fastjson.JSON;
import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import com.gemframework.model.BaseResultData;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: CopyUtils.java
 * @Package: com.gemframework.util
 * @Date: 2019/11/28 23:12
 * @Version: v1.0
 * @Description: 对象返回

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Slf4j
public class GemResponse {


    public static void returnResult(HttpServletRequest request, HttpServletResponse response, ResultCode resultCode, ResultURL url) throws IOException {
        request.setCharacterEncoding(GemConstant.Character.UTF8);
        response.setCharacterEncoding(GemConstant.Character.UTF8);

        //判断请求头。如果包含"Authorization"则为第三方应用请求
        if(request.getHeader(GemConstant.Auth.HEADER_AUTHOR) == null){
            log.info("访问类型：pages");
            String redirectUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+url.getUrl();
            response.sendRedirect(redirectUrl);
        }else{
            log.info("访问类型：api");
            response.getWriter().println(JSON.toJSONString(BaseResultData.ERROR(resultCode)));
        }
    }
}
