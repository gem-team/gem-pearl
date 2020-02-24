package com.gemframework.common.utils;



import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class GemIPHandler {

    public static void main(String[] args) {
        ipToAddress("171.217.63.114");
    }


    public static String ipToAddress(String ip){
        String resout = "";
        try{
            String str = getHTTPJson("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            System.out.println(str);
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject obj2 =  (JSONObject) obj.get("data");
            Integer code = (Integer) obj.get("code");
            if(code == 0){
                String counntry = (((String)obj2.get("country")).equals("")||
                        ((String)obj2.get("country")).equalsIgnoreCase("xx"))?"":(String)obj2.get("country")+",";
                String area = (((String)obj2.get("area")).equals("")||
                        ((String)obj2.get("area")).equalsIgnoreCase("xx"))?"":(String)obj2.get("area")+",";
                String city = (((String)obj2.get("city")).equals("")||
                        ((String)obj2.get("city")).equalsIgnoreCase("xx"))?"":(String)obj2.get("city")+",";
                String isp = (((String)obj2.get("isp")).equals("")||
                        ((String)obj2.get("isp")).equalsIgnoreCase("xx"))?"":(String)obj2.get("isp")+",";
                resout =  counntry+area+city+isp;
                resout = resout.substring(0,resout.length()-1);
            }else{
                resout =  "IP地址有误";
            }
        }catch(Exception e){
            log.info(e.getMessage());
            resout = "获取IP地址异常："+e.getMessage();
        }
        System.out.println("result: " + resout);
        return resout;
    }

    /**
     * 通过Get请求获取JSON返回
     * @param urlStr
     * @return
     */
    public static String getHTTPJson(String urlStr) {
        try
        {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200)
            {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return jsonStr;
    }
}