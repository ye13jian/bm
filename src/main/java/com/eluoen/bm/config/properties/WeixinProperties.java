package com.eluoen.bm.config.properties;

import com.eluoen.bm.modular.mp.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信公众号配置
 *
 * @author eluoen
 * @Date 2018/12/23
 */
@Component
@ConfigurationProperties(prefix = WeixinProperties.PREFIX)
public class WeixinProperties {

    private final Logger log = LoggerFactory.getLogger(WeixinProperties.class);

    public static final String PREFIX = "weixin";

    private String ip;
    private String token;
    private String appid;
    private String appsecret;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        log.info("-----------------------------------------"+ip);
        this.ip = ip;
        StringUtil.IP=ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        StringUtil.token=token;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
        StringUtil.AppId=appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
        StringUtil.AppSecret=appsecret;
    }

    @Override
    public String toString() {
        return "WeixinProperties{" +
                "ip='" + ip + '\'' +
                ", token='" + token + '\'' +
                ", appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                '}';
    }
}
