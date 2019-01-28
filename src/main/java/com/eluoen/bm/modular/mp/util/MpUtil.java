package com.eluoen.bm.modular.mp.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个是自己写的工具类
 * eluoen
 * 2018-12-12
 */
public class MpUtil {


    //百度ak密钥
    private final static String ak = "54fc34735729786f0c4c375156e1e7ee";

    /**
     * 获取当前网络ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")
                    || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();//返回本地主机
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();//返回ip地址字符串
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }



    public static String httpClient(String params, String url,String requestMethod) {

        //System.out.println(url);

        StringBuffer bufferRes = new StringBuffer();

        try {

            URL realUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 连接超时
            conn.setConnectTimeout(10000);

            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(10000);

            // 请求方式
            conn.setRequestMethod(requestMethod);

            // 发送POST请求必须设置如下两行
            //以后就可以使用conn.getOutputStream().write()
            conn.setDoOutput(true);
            //以后就可以使用conn.getInputStream().read();
            conn.setDoInput(true);

            //设置字符编码
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            //
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            conn.connect();

            // 获取URLConnection对象对应的输出流
            if(params!=null&&!params.isEmpty()){
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());

                // 发送请求参数
                out.write(params);
                out.flush();
                out.close();
            }


            InputStream in = conn.getInputStream();

            BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));

            String valueString = null;

            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }

            System.out.println(url+"\n"+bufferRes.toString());

            in.close();

            if (conn != null) {

                // 关闭连接

                conn.disconnect();

            }

        } catch (Exception e) {

            e.printStackTrace();
            bufferRes.append(e.getMessage());

        }
        return bufferRes.toString();
    }

    /**
     * 获取省市
     * @param ip
     * @return
     */
    public static Map<String,String> getLocation(String ip){

        Map<String,String> map = new HashMap<String, String>();
        String province = "";
        String city = "";
        try {
            String url = "http://api.map.baidu.com/location/ip?ak="+ak+"&ip="+ip;
            String result = httpClient(null, url, "GET");

            JSONObject jsonObject = JSONObject.parseObject(result);
            //System.out.println("content:"+jsonObject.get("content"));

            Map<String,Object> m2 = (Map<String, Object>) jsonObject.get("content");

            //System.out.println(m2.get("address_detail"));

            Map<String,Object> m3 = (Map<String, Object>) m2.get("address_detail");

            //System.out.println(m3.get("province")+" "+m3.get("city"));

            province = m3.get("province").toString();
            city = m3.get("city").toString();
            province = province.replaceAll("省", "");
            province = province.replaceAll("市", "");
            city = city.length()>2?city.substring(0, city.length()-1):city;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        province = province.equals("")?"":province;
        city = city.equals("")?"":city;

        map.put("province", province);
        map.put("city", city);
        System.out.println(map);
        return map;

    }





    public static void main(String[] args) {


        String ip = "120.76.225.234";
        getLocation(ip);




    }





}
