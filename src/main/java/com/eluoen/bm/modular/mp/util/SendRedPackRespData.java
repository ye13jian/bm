package com.eluoen.bm.modular.mp.util;

/**
 * 微信红包响应对象
 * 
 * @author Administrator
 * @date 2015-5-21
 */
public class SendRedPackRespData {
	
	
	
	

	private String return_code;
	private String return_msg;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String mch_billno;
	private String mch_id;
	private String wxappid;
	private String re_openid;
	private int total_amount;
	private String send_listid;
	private String send_time;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String resultCode) {
		result_code = resultCode;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String errCode) {
		err_code = errCode;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String errCodeDes) {
		err_code_des = errCodeDes;
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

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
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

	public String getSend_listid() {
		return send_listid;
	}

	public void setSend_listid(String sendListid) {
		send_listid = sendListid;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}

}
