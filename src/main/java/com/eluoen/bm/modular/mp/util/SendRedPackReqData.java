package com.eluoen.bm.modular.mp.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信红包请求对象
 * 
 * @author Administrator
 * @date 2015-5-19
 */
public class SendRedPackReqData {

	// 每个字段具体的意思请查看API文档
	private String nonce_str;
	private String sign;
	private String mch_billno;
	private String mch_id;
	private String sub_mch_id;
	private String wxappid;
	private String nick_name;
	private String send_name;
	private String re_openid;
	private int total_amount;
	private int min_value;
	private int max_value;
	private int total_num;
	private String wishing;
	private String client_ip;
	private String act_name;
	private String remark;
	private String logo_imgurl;
	private String share_content;
	private String share_url;
	private String share_imgurl;

	public SendRedPackReqData(String nick_name,String send_name,String reOpenid, int totalAmount, int minValue, int maxValue,
			int totalNum, String wishing, String clientIp, String actName,
			String remark, String logoImgurl,
			String shareContent, String shareUrl, String shareImgurl) {

		setWxappid(Configure.getAppid());
		setMch_id(Configure.getMchid());
		setNick_name(nick_name);
		setSend_name(send_name);

		//商户订单
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		setMch_billno(getMch_id()+sdf.format(new Date()));

		re_openid = reOpenid;
		total_amount = totalAmount;
		min_value = minValue;
		max_value = maxValue;
		total_num = totalNum;
		this.wishing = wishing;
		client_ip = clientIp;
		act_name = actName;
		this.remark = remark;
		logo_imgurl = logoImgurl;
		share_content = shareContent;
		share_url = shareUrl;
		share_imgurl = shareImgurl;
		
		//随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mchBillno) {
		mch_billno = mchBillno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mchId) {
		mch_id = mchId;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String subMchId) {
		sub_mch_id = subMchId;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nickName) {
		nick_name = nickName;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String sendName) {
		send_name = sendName;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String reOpenid) {
		re_openid = reOpenid;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int totalAmount) {
		total_amount = totalAmount;
	}

	public int getMin_value() {
		return min_value;
	}

	public void setMin_value(int minValue) {
		min_value = minValue;
	}

	public int getMax_value() {
		return max_value;
	}

	public void setMax_value(int maxValue) {
		max_value = maxValue;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int totalNum) {
		total_num = totalNum;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String clientIp) {
		client_ip = clientIp;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String actName) {
		act_name = actName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogo_imgurl() {
		return logo_imgurl;
	}

	public void setLogo_imgurl(String logoImgurl) {
		logo_imgurl = logoImgurl;
	}

	public String getShare_content() {
		return share_content;
	}

	public void setShare_content(String shareContent) {
		share_content = shareContent;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String shareUrl) {
		share_url = shareUrl;
	}

	public String getShare_imgurl() {
		return share_imgurl;
	}

	public void setShare_imgurl(String shareImgurl) {
		share_imgurl = shareImgurl;
	}
	
	/**
	 * sign签名
	 * @date 2015-5-19
	 * @return
	 */
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


	public static void main(String[] args) {
		
		//EFDBB918BA1A007EF0054501DE3118A2
		//http://www.aem315.net/815/wxRedPack?nick_name=蓝天豚&send_name=蓝天豚&wishing=哇噻，手气不错！&act_name=蓝天豚红包&remark=remark
		//1278128801
		
		SendRedPackReqData sendRedPackReqData = new SendRedPackReqData("蓝天豚","蓝天豚","oPhnTvm75m4VIE3AtKT4OQUjx2ag", 1, 1, 1, 1, "哇噻，手气不错！", "113.108.11.50", "蓝天豚红包", "remark", null, null, null, null);

	}
	
}
