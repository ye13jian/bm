package com.eluoen.bm.modular.mp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WeiXinInterfacetUtil {

	private final static Logger log = LoggerFactory.getLogger(WeiXinInterfacetUtil.class);
	
	public final static String GET = "GET";
	public final static String POST = "POST";

	public static String httpClient(String params, String url,String requestMethod) {

		System.out.println(url+":"+params);
		
		StringBuffer bufferRes = new StringBuffer();

		try {

			URL realUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

			// 连接超时
			conn.setConnectTimeout(25000);

			// 读取超时 --服务器响应比较慢,增大时间

			conn.setReadTimeout(25000);

			HttpURLConnection.setFollowRedirects(true);

			// 请求方式
			conn.setRequestMethod(requestMethod);

			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");

			conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");

			conn.connect();

			// 获取URLConnection对象对应的输出流

			OutputStreamWriter out = new OutputStreamWriter(conn
					.getOutputStream());

			// 发送请求参数

			// out.write(URLEncoder.encode(params,"UTF-8"));

			out.write(params);

			out.flush();

			out.close();

			InputStream in = conn.getInputStream();

			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));

			String valueString = null;

			while ((valueString = read.readLine()) != null) {

				bufferRes.append(valueString);

			}

			System.out.println("result:"+bufferRes.toString());

			in.close();

			if (conn != null) {

				// 关闭连接

				conn.disconnect();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return bufferRes.toString();
	}
	
	/**
	 * 基础支持 获取access_token
	 * @return
	 */
	public static JSONObject accessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+StringUtil.AppId+"&secret="+StringUtil.AppSecret+"";
		String resultMsg = httpClient("", url, GET);
		return JSON.parseObject(resultMsg);
	}

	/**
	 * 基础支持 获取access_token
	 * @return
	 */
	private static String getAccessToken(){
		JSONObject token = accessToken();
		return token.get("access_token").toString();
	}


	/**
	 * 基础支持 获取用户基本信息接口
	 * @date 2018年12月13日
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static JSONObject cgi_userInfoMap(String accessToken, String openid){
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
		String resultMsg = httpClient("", url, GET);
		return JSON.parseObject(resultMsg);
	}


	/*public JSONObject getJSONObject(String json){
		return JSON.parseObject(json);
	}*/
	
	
	/**
	 * 获取网页授权oauth2的access_tokenMap
	 * @return
	 */
	public static JSONObject oauth2AccessTokenMap(String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+StringUtil.AppId+"&secret="+StringUtil.AppSecret+"&code="+code+"&grant_type=authorization_code";
		String resultMsg = httpClient("", url, GET);
		return JSON.parseObject(resultMsg);
	}
	
	/**
	 * 网页获取用户基本信息
	 * @date 2015年10月16日
	 * @param accessToken
	 * @param reOpenid
	 * @return
	 */
	public static JSONObject userinfoMap(String accessToken, String reOpenid){
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+reOpenid+"&lang=zh_CN";
		String resultMsg = httpClient("", url, GET);
		//String repTxt = resultMsg.substring(resultMsg.indexOf("["), resultMsg.lastIndexOf("]")+1);
		//resultMsg = resultMsg.replace(repTxt, "\"\"");
		return JSON.parseObject(resultMsg);
	}
	
	/**
	 * 获取jsapi_ticket票据
	 * @date 2019-01-14
	 * @param access_token
	 * @return
	 */
	public static JSONObject jsapiTicket(String access_token){
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		String resultMsg = httpClient("", url, GET);
		return JSON.parseObject(resultMsg);
	}

	/**
	 * 创建自定义菜单
	 * @return
	 */
	/*public static String createMenu(){
		String accessToken = accessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		String params = "{\"button\":[{\"type\":\"view\",\"name\":\"微官网\",\"url\":\"http://www.utap315.net/w/M1001.html\"},{\"name\":\"解决方案\",\"sub_button\":[{\"type\":\"view\",\"name\":\"微信防伪\",\"url\":\"http://www.utap315.net/w/M2001.html\"},{\"type\":\"view\",\"name\":\"二维码防伪\",\"url\":\"http://www.utap315.net/w/M2002.html\"},{\"type\":\"view\",\"name\":\"溯源管理系统\",\"url\":\"http://www.utap315.net/w/M2003.html\"},{\"type\":\"view\",\"name\":\"防窜货管理系统\",\"url\":\"http://www.utap315.net/w/M2004.html\"},{\"type\":\"view\",\"name\":\"数字化仓库\",\"url\":\"http://www.utap315.net/w/M2005.html\"}]},{\"name\":\"会员服务\",\"sub_button\":[{\"type\":\"click\",\"name\":\"我的积分\",\"key\":\"M3001\"},{\"type\":\"click\",\"name\":\"积分兑换\",\"key\":\"M3002\"},{\"type\":\"click\",\"name\":\"幸运抽奖\",\"key\":\"M3003\"},{\"type\":\"click\",\"name\":\"防伪验证\",\"key\":\"M3004\"}]}]}";
		return httpClient(params, url, POST);
	}*/
	
	/**
	 * 生成场景动态二维码
	 * @date 2016-11-23
	 * @param access_token
	 * @param qrcode
	 * @return
	 */
	/*public static String create_qrcode_ticket(String access_token,String scene_id){
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token+"";
		String params = "{\"expire_seconds\": 60, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+scene_id+"}}}";
		//System.out.println("create_qrcode_ticket="+scene_id);
		String resultMsg =  httpClient(params, url, GET);
		Type type = new TypeToken<Map<String,String>>(){}.getType();
		Map<String,String> map = new Gson().fromJson(resultMsg, type);
		return map.get("ticket");
	}*/
	
	/**
	 * 生成永久场景动态二维码
	 * @date 2016-11-23
	 * @param access_token
	 * @param qrcode
	 * @return
	 */
	/*public static String create_qrcode_limit_ticket(String access_token,String scene_id){
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token+"";
		String params = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+scene_id+"}}}";
		//System.out.println("create_qrcode_ticket="+scene_id);
		String resultMsg =  httpClient(params, url, GET);
		Type type = new TypeToken<Map<String,String>>(){}.getType();
		Map<String,String> map = new Gson().fromJson(resultMsg, type);
		return map.get("ticket");
	}*/
	
	/**
	 * 公众号获取用户基本信息
	 * @date 2015-11-23
	 * @param access_token
	 * @param openid
	 * @return
	 */
	/*public static String cgibinUserInfo(String access_token,String openid){
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
		return httpClient("", url, GET);
	}*/


	/**
	 * 创建分组https://www.cnblogs.com/zuochuang/p/4721653.html
	 * @param ACCESS_TOKEN
	 * @param groupName
	 * @return
	 */
	/*public static JSONObject createGroup(String ACCESS_TOKEN,String groupName){
		String url = "http://api.weixin.qq.com/cgi-bin/groups/create?access_token="+ACCESS_TOKEN;
		//{"group":{"name":"test"}}
		String params = "{\"group\":{\"name\":\""+groupName+"\"}}";
		log.info(params);
		String resultMsg = httpClient(params,url,POST);
		return JSON.parseObject(resultMsg);
	}*/



    /**
     * 获取所有标签
     * @param ACCESS_TOKEN
     * @return
     */
    public static JSONObject getTags(String ACCESS_TOKEN){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token="+ACCESS_TOKEN;
        String resultMsg = httpClient("",url,GET);
        return JSON.parseObject(resultMsg);
    }

    /**
     * 获取标签下粉丝列表
     * @param ACCESS_TOKEN
     * @return
     */
    public static JSONObject getTagUsers(String ACCESS_TOKEN, Integer tagId){
        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token="+ACCESS_TOKEN;
        String params = "{   \"tagid\" : "+tagId+",   \"next_openid\":\"\" }";
        String resultMsg = httpClient(params,url,GET);
        return JSON.parseObject(resultMsg);
    }

	/**
	 * 获取用户身上的标签列表
	 * @param ACCESS_TOKEN
	 * @param openid
	 * @return
	 */
	public static JSONObject getUseridlist(String ACCESS_TOKEN, String openid){
		String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token="+ACCESS_TOKEN;
		String params = "{   \"openid\" : \""+openid+"\" }";
		String resultMsg = httpClient(params,url,POST);
		return JSON.parseObject(resultMsg);
	}

    /**
     * 创建标签
     * @param ACCESS_TOKEN
     * @param tagName
     * @return
     */
    public static JSONObject createTags(String ACCESS_TOKEN, String tagName){
        log.info("createTags:"+tagName);
		log.info(Charset.defaultCharset()+"");
		log.info(System.getProperties()+"");
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token="+ACCESS_TOKEN;
        //{   "tag" : {     "name" : "广东"//标签名   } }
        String params = "{   \"tag\" : {     \"name\" : \""+tagName+"\"   } }";
		log.info("createTags:"+params);
        String resultMsg = httpClient(params,url,POST);
        return JSON.parseObject(resultMsg);
    }

    /**
     * 编辑标签
     * @param ACCESS_TOKEN
     * @param tagId
     * @param tagName
     * @return
     */
    public static JSONObject updateTags(String ACCESS_TOKEN, Integer tagId, String tagName){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token="+ACCESS_TOKEN;
        //{   "tag" : {     "id" : 134,     "name" : "广东人"   } }
        String params = "{   \"tag\" : {     \"id\" : "+tagId+",     \"name\" : \""+tagName+"\"   } }";
        String resultMsg = httpClient(params,url,POST);
        return JSON.parseObject(resultMsg);
    }

    /**
     * 删除标签
     * @param ACCESS_TOKEN
     * @param tagId
     * @return
     */
    public static JSONObject deleteTags(String ACCESS_TOKEN, Integer tagId){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token="+ACCESS_TOKEN;
        //{   "tag":{        "id" : 134   } }
        String params = "{   \"tag\":{        \"id\" : "+tagId+"   } }";
        String resultMsg = httpClient(params,url,POST);
        return JSON.parseObject(resultMsg);
    }

    /**
     * 批量为用户打标签
     * @param ACCESS_TOKEN
     * @param tagId
     * @return
     */
    public static JSONObject batchtaggingTags(String ACCESS_TOKEN, String openids, Integer tagId){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="+ACCESS_TOKEN;
        //{   "openid_list" : [//粉丝列表
        //"ocYxcuAEy30bX0NXmGn4ypqx3tI0",
        //"ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"   ],
        //"tagid" : 134 }
        String params = "{   \"openid_list\" : "+openids+",   \n" + "\"tagid\" : "+tagId+" }";
        String resultMsg = httpClient(params,url,POST);
        return JSON.parseObject(resultMsg);
    }


	/**
	 * 模板消息：获取设置的行业信息
	 * @param ACCESS_TOKEN
	 * @return
	 */
	public static JSONObject templateGetIndustry(String ACCESS_TOKEN){
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+ACCESS_TOKEN;
		String resultMsg = httpClient("",url,GET);
		return JSON.parseObject(resultMsg);
	}


	/**
	 * 模板消息：获取模板列表
	 * @param ACCESS_TOKEN
	 * @return
	 */
	public static JSONObject templateGetAllPrivateTemplate(String ACCESS_TOKEN){
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="+ACCESS_TOKEN;
		String resultMsg = httpClient("",url,GET);
		return JSON.parseObject(resultMsg);
	}

	/**
	 * 模板消息：发送模板消息
	 * @param params
	 * @return
	 */
	public static JSONObject templateSend(String params){
		String ACCESS_TOKEN = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ACCESS_TOKEN;
		log.info(params);
		String resultMsg = httpClient(params,url,POST);
		return JSON.parseObject(resultMsg);
	}




	/**
	 * 
	 * @param args
	 */
	public static void tag(String[] args) {
        //{"tags":[{"name":"星标组","count":0,"id":2},{"name":"testupdate","count":0,"id":100},{"name":"test1","count":0,"id":101}]}
		String openid = "o5kXljm1CXR4fopA_mEA8z8lW5Rw";

		JSONObject token = accessToken();
		String access_token = token.get("access_token").toString();
		//log.info("token",token);

		//String[] openidArr = new String[]{"o5kXljm1CXR4fopA_mEA8z8lW5Rw"};
		//String openids = JSONObject.toJSONString(openidArr);


		//JSONObject tags = batchtaggingTags(token.get("access_token").toString(),openids,101);
		//log.info("tags:"+tags);

        //JSONObject tags = getTagUsers(access_token,101);

//{"tags":[{"name":"星标组","count":0,"id":2},{"name":"testupdate","count":0,"id":100},{"name":"test1","count":0,"id":101},{"name":"湖南","count":0,"id":102},{"name":"最易上手T1册","count":1,"id":103},{"name":"书名不详","count":1,"id":104}]}

		//JSONObject tags = getTags(access_token);
		//JSONObject tags = deleteTags(access_token,107);



		//JSONObject tags = getUseridlist(access_token,openid);

        try {
            String str = "测试中文啊4";
            log.info(Charset.defaultCharset()+"");
            String tagName = new String(str.getBytes(),"UTF-8");
            JSONObject tags = createTags(access_token,tagName);
            log.info("tags:"+tags);
        }catch (Exception e){
            e.printStackTrace();
        }

	}


	public static void main(String[] args) {
		StringUtil.AppId="wx350478a629ab49bb";
		StringUtil.AppSecret="b34bb35b941547d58fcce42f4b154711";
		JSONObject token = accessToken();
		String access_token = token.get("access_token").toString();
		//templateGetAllPrivateTemplate(access_token);

		//templateSend
		String touser = "o5kXljm1CXR4fopA_mEA8z8lW5Rw";
		String link = "http://www.baidu.com";
		String template_id = "tF4A8BxN1IT9UgLunCpOqC8O4fBnyTXt3whq-GdDCfw";
		//String data = "{\"first\":{\"value\":\"好朋友啊\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"怎么样？\",\"color\":\"#173177\"}}";

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("touser","o5kXljm1CXR4fopA_mEA8z8lW5Rw");
		map.put("template_id","WeyunRCXtxVl8HfKfpatZtx_bKjIryCEji_0iWiRFbk");
		map.put("url","http://www.baidu.com");

		Map<String,Object> data = new HashMap<String,Object>();
		Map<String,Object> first = new HashMap<String,Object>();
		first.put("value","陈志云");
		first.put("color","#173177");
		data.put("nickname",first);
		Map<String,Object> keyword1 = new HashMap<String,Object>();
		keyword1.put("value","星沙三一街区");
		keyword1.put("color","#173177");
		data.put("location",keyword1);
		Map<String,Object> remark = new HashMap<String,Object>();
		remark.put("value","快快去和他聊天吧！");
		remark.put("color","#173177");
		data.put("remark",remark);

		map.put("data",data);

		String params = JSONObject.toJSONString(map);

		templateSend(params);
	}

}
