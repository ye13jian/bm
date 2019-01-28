package com.eluoen.bm.modular.mp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class StringUtil {

	private final static Logger log = LoggerFactory.getLogger(StringUtil.class);

	public static String IP;
	public static String token;
	public static String AppId;
	public static String AppSecret;

	//授权域名
	//public final static String IP = "http://niqan.nat300.top/wylrb";
	//public final static String IP = "http://wylrb.niqan.com/wylrb";
	//public static String IP;
	//public final static String token = "eluoen";//
	//public final static String AppId = "wx350478a629ab49bb";//测试公众号
	//public final static String AppSecret = "b34bb35b941547d58fcce42f4b154711";//测试公众号
	//public final static String AppId = "wx98010681ca42c2db";//许乐飞
	//public final static String AppSecret = "024bfde3787d5c46b54f003b34ff08fb";//许乐飞

	/**
	 * 加密/校验流程如下： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
	 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	/**
	 * 与接口配置信息中的 token 要一致，这里赋予什么值，在接口配置信息中的Token就要填写什么值，
	 * 两边保持一致即可，建议用项目名称、公司名称缩写等，我在这里用的是项目名称weixinface
	 */
	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将 token, timestamp, nonce 三个参数进行字典排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行 shal 加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		System.out.println(tmpStr + " " + signature);
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		// TODO Auto-generated method stub
		String strDigest = "";
		for (int i = 0; i < digest.length; i++) {
			strDigest += byteToHexStr(digest[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		// TODO Auto-generated method stub
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];

		String s = new String(tempArr);
		return s;	
	}
	
	/**
     * 将字节数组转换为十六进制字符串
     * @date 2015-3-19
     * @param bs
     * @return
     */
    private static String byteToHexStr(byte[] bs) {
        Formatter formatter = new Formatter();
        for (byte b : bs)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
	/**
	 * 正则表达式数字验证
	 * @date 2015-3-19
	 * @param str
	 * @return
	 */
    public static boolean isNumber(String str)
    {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match=pattern.matcher(str);
        return match.matches(); 
    }
    
    /**
     * 生成随机串
     * @date 2015-3-19
     * @return
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成时间戳
     * @date 2015-3-19
     * @return
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * js签名
     * @date 2015-3-19
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHexStr(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("appId", AppId);
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
    
    
    
    /**
     * 微信支付签名(注意此处返回必须是Map<String,Object>类型，不然支付不成功)
     * @date 2017年5月18日
     * @param jsapi_ticket
     * @param url
     * @return
     * @throws IllegalAccessException 
     */
    public static Map<String, Object> sign_md5(String prepay_id) throws IllegalAccessException {
        Map<String, Object> ret = new HashMap<String, Object>();
        String appId = Configure.getAppid();
        String nonceStr = createNonceStr();
        String timeStamp = createTimestamp();
        //appId, timeStamp, nonceStr, package, signType。
        ret.put("appId", appId);
        ret.put("timeStamp", timeStamp);
        ret.put("nonceStr", nonceStr);
        ret.put("signType", "MD5");
        ret.put("package", prepay_id);
        
        System.out.println("sign ret="+ret);
        String paySign = Signature.getSign(ret);
        
        ret.put("paySign", paySign);

        return ret;
    }






}
