package com.ebupt.justholdon.net;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ebupt.boxlunch.exception.NoConnectException;
import com.ebupt.boxlunch.exception.ParserException;
import com.ebupt.boxlunch.model.Comment;
import com.ebupt.boxlunch.model.MeishidarenUser;
import com.ebupt.boxlunch.model.NetResult;
import com.ebupt.boxlunch.model.TweetMsg;
import com.ebupt.boxlunch.model.UpdateDetail;
import com.ebupt.boxlunch.model.UserSimple;
import com.ebupt.boxlunch.safty.DigestAlgorithms;
import com.ebupt.boxlunch.util.JsonParser;
import com.ebupt.boxlunch.util.ShortCut;

public class NetEngine {//218.249.60.68:9000  //10.1.69.113:9000
	
	public static final String SERVER_IP_EB="http://10.1.69.113:9000/";
	public static final String SERVER_IP_OUT="http://218.249.60.69:9000/";
	public static final String FOOD_BASIC = "food/index.php";
	public static String BSAE_URL = "http://10.1.69.113:9000/food/index.php";//"https://10.1.60.162:8443";//"http://220.194.57.165:8088" ;
	
	private final String TAG = "NetEngine";
	
	
	private final String LOGIN_PAGE = "login" ;
	private final String MODIFY_PASSWORD_PAGE = "resetPassword" ;
	private final String MODIFY_USERINFO_PAGE = "modifyUserInfo" ;
	private final String MODIFY_USERNAME_PAGE = "resetUsername" ;
	private final String TWEET_PAGE = "toTweet" ;
	private final String GET_FAV_PAGE = "getFavorites" ;
	private final String ADD_FOCUS_PAGE = "addFocus" ;
	private final String GET_USERINFO_PAGE = "getUserInfo" ;
	private final String GET_TWEET_PAGE = "getUserTweets" ;
	private final String GET_GOOD_TWEET_PAGE = "getUserGoodTweets" ;
	private final String DO_SEARCH = "doSearch" ;
	private final String GET_FOLLOWERS_PAGE = "getUserFollowers" ;
	private final String GET_FOCUSERS_PAGE = "getUserFocus" ;
	private final String DO_COMMENT_PAGE = "doComment" ;
	private final String GET_COMMENTS_PAGE = "getComments" ;
	private final String DO_LIKE_PAGE = "doGood" ;
	private final String CANCEL_LIKE_PAGE = "cancelGood" ;
	private final String GET_LIKES_PAGE = "getGoods" ;
	private final String GET_CAT_TWEET_PAGE = "getCategoryTweets" ;
	private final String UPLOAD_PIC_PAGE = "uploadPic" ;
	private final String GET_MSG_PAGE = "getMessages" ;
	private final String UPLOAD_AVA_PAGE = "uploadAvatar" ;
	private final String SHARE_TWEET = "shareTweet" ;
	private final String GET_WEIBO_FRI = "socialFriends";
	private final String GET_ONE_TWEET = "showOneTweet";
	
	private final String BIND_WEIBO_PAGE = "bind" ;
	private final String UNBIND_WEIBO_PAGE = "unbind" ;
	private final String LOGIN_WEIBO_PAGE = "socialLogin" ;
	private final String QUERY_BIND_WEIBO_PAGE = "querybindweibo.do" ;
	private final String GET_MESSAGE = "getMessages" ;
	private final String GET_USER_GOOD = "getUserGoodTweets" ;
	
	private final String GET_TOP_PAGE = "getTopTweets" ;
	private final String GET_MY_SHARE = "getMyShares" ;
	private final String GET_TOP_USERS = "getTopUsers" ;
	private final String GET_TOP_SEARCH_WORDS = "getTopSearchWords" ;
	private final String DELETETWEET = "deleteTweet";
	

	//  ?¸å?URL
	private final String Meishidaren_BASIC_URL = BSAE_URL+ "/api/" ;

	//private final String REGISTER_PAGE = "reg.do" ;
	

	//æ³¨å??»å? ?¸å?URL
	private final String REGISTER_BASIC_URL = BSAE_URL+ "/api/" ;
	private final String REGISTER_PAGE = "register" ;
	
	
	
	public static final String APPUPDATE_BASIC_URL = "version" ;
	private final String NO_ANSWER = "å¯¹ä?èµ·ï????ä¸???¡å??·å?è¿??";
		
	private Context context = null;
	
	public NetEngine(Context ctx) {
		context = ctx;
	}
	
	

//	public NetResult doMeishidarenGet(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
//		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
//		sb.append(page);
//		String ret = null;
//		NetResult result = new NetResult() ;
//		ret = NetUtils.openUrl(sb.toString(), "get", bundle, context);
//		//Log.i(TAG, "ret = "+ret);
//		result = JsonParser.parseNetResult(ret);
//		Log.i(page, result.toString());
//		return result ;
//	}
	public NetResult doMeishidarenPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
		//Log.i(TAG, "ret = "+ret);
		NetResult  result = JsonParser.parseNetResult(ret);
		Log.i(page, result.toString());
		return result ;	
	}
	
	public NetResult doMeishidarenGet(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.openUrl(sb.toString(), "get", bundle, context);
		//Log.i(TAG, "ret = "+ret);
		NetResult  result = JsonParser.parseNetResult(ret);
		Log.i(page, result.toString());
		return result ;	
	}
	
	public NetResult doMeishidarenListPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
		//Log.i(TAG, "ret = "+ret);
		NetResult  result = JsonParser.parseListResult(ret);
		Log.i(page, result.toString());
		return result ;	
	}
	
	public NetResult doTopSearchWordsGet(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		NetResult result = new NetResult() ;
		ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
		Log.i(page, result.toString());
		result = JsonParser.parseTopSearchWordsResult(ret);
		if (result== null ) Log.e(TAG,"ERROR! result is null!");
		else Log.e(TAG,"result error message:"+result.getErrorMessage()+",result error code:"+result.getErrorCode());
		Log.i(TAG, "result = "+result.getRetContent());
		return result ;
	}
	
	public HashMap<String,String> doSearchPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
		//Log.i(TAG, "ret = "+ret);
		HashMap<String,String> result = JsonParser.parseSearchListResult(ret);
		Log.i(page, result.toString());
		return result ;	
	}
	
	private NetResult doMeishidarenUpload(String page, Bundle bundle, InputStream is,String filename) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.uploadPic(sb.toString(), "post", bundle, context,is ,filename);
		//Log.i(TAG, "ret = "+ret);
		NetResult result = JsonParser.parseNetResult(ret);
		Log.i(page, result.toString());
		return result ;
	}
	
	private NetResult doMeishidarenPic(String page, Bundle bundle, InputStream is,String filename) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.uploadPic(sb.toString(), "post", bundle, context,is ,filename);
		//Log.i(TAG, "ret = "+ret);
		NetResult result = JsonParser.parsePicNetResult(ret);
		Log.i(page, result.toString());
		return result ;
	}
	
	
	public NetResult doPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
		StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
		sb.append(page);
		String ret = null;
		ret = NetUtils.openUrl(sb.toString(), "get", bundle, context);
//		Log.i(TAG, "ret = "+ret);
		NetResult  result = JsonParser.parseNetResult(ret);
		Log.i(page, result.toString());
		return result ;	
	}
	

	
	
//  /*4.2  è§£é?ç»??- ???8.2
//	http://203.181.161.230:8080/chailing/api/unbindweibo.do? 
//	???è¯´æ?:
//   	  msisdn : ?¨æ?????·ç?
//	  psw : ?¨æ?å¯??----ç¼??urlencode, utf8
//	è¿??ç»??:
//	  {
//		"errorCode":0,    --???è¿??0
//		"errorMessage":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
//	  }*/
//	public void unbindWeibo(String pNum,String psw) throws IOException, ParserException, NoConnectException{
//		Bundle bundle = new Bundle();
//		bundle.putString("msisdn", pNum);
//		bundle.putString("psw", psw);
//		NetResult result =  this.doMeishidarenGet(UNBIND_WEIBO_PAGE, bundle);
//
//		if(!result.getErrorCode().equals("0")){
//			throw new ParserException(result.getErrorMessage() ) ;
//		}
//	}
	
	
// /* 4.3  æ£?????ç»??- ???4.4.	?¥è??¨æ?ä¿¡æ??¥å?
//	http://203.181.161.230:8080/chailing/api/querybindweibo.do? 
//	???è¯´æ?:
//   	  msisdn : ?¨æ?????·ç?
//	  psw : ?¨æ?å¯??----ç¼??urlencode, utf8
//	è¿??ç»??:
//	  {
//		"errorCode":0,    --???è¿??0
//		"errorMessage":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
//		"weiboFlag":0 ---- 0:å·²ç?å¼??  1:å°??å¼??
//	  }
//	 */	
//	public boolean queryBindWeibo(String pNum,String psw) throws IOException, ParserException, NoConnectException{
//			Bundle bundle = new Bundle();
//			bundle.putString("msisdn", pNum);
//			bundle.putString("psw", psw);
//			Log.v("WeiBo", "???start") ;
//			NetResult result =  this.doMeishidarenGet(QUERY_BIND_WEIBO_PAGE, bundle);
//
//			if(!result.getErrorCode().equals("0")){
//				throw new ParserException(result.getErrorMessage() ) ;
//			} else{
//				return JsonParser.queryBindWeibo(result.getRetContent())  ;
//			}
//		}
//	
	
	
	
	
	
	/*
	1.1	å¿??æ³¨å?
	method:register

	è°?????
 	??

	è¿??é¡¹è????
	è¿??é¡???è¿???å¼?
	status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
	msg	æ¶??	??????ï¼??å¤±è´¥???
	content	è¿?????	JSONObject
	content.uid	?¨æ???å­??ä¸?
	content.password	å¯??	å­??ä¸?
		

	ç¤ºä?
	192.168.0.137/api/register
	{
    	"status": "0",
    	"msg": "",
    	"content": {
        	"uid": 3768,
        	"username": "Ut1D6X",
        	"password": "bwgrWjOz"
    	}
	}

	 */
	public MeishidarenUser quickRegister() throws IOException, ParserException, NoConnectException{
		MeishidarenUser meishidarenUser = null;
		Bundle bundle = new Bundle();
//		bundle.putString("msisdn", msisdn);
//		bundle.putString("psw", ""+psw);
//		bundle.putString("lastModifyTime", lastModifyTime);
		NetResult result =  this.doMeishidarenPost(REGISTER_PAGE, bundle);
		
		if(result.getErrorCode().equals("0")){
			
			meishidarenUser  = JsonParser.parseUserInfo(result.getRetContent()) ;
			
			
		}else{
			throw new ParserException(result.getErrorMessage() ) ;
		}
		return meishidarenUser;
	}
	
	
//	public NetResult doRegisterPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
//		StringBuilder sb = new StringBuilder(REGISTER_BASIC_URL);
//		sb.append(page);
//		String ret = null;
//		NetResult result = new NetResult() ;
//		ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
////		Log.i(TAG, "ret = "+ret);
//		Log.i(page, result.toString());
//		result = JsonParser.parserRegisterResult(ret);
//		if (result== null ) Log.e(TAG,"ERROR! result is null!");
//		else Log.e(TAG,"result error message:"+result.getErrorMessage()+",result error code:"+result.getErrorCode());
//		Log.i(TAG, "result = "+result.getRetContent());
//		
//		return result ;
//	}
	
	/*
	 *  1.2	?»å?
	methodï¼?ogin

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
type	?»å?ç±»å?	0ï¼?????å½?
1ï¼??æµ?¾®??´¦??
2ï¼?ººäººè´¦??
key	?´æ?å¯??	å­??ä¸?

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.uid	?¨æ???å­??ä¸?
content.password	å¯??	å­??ä¸?
content.*	?¶ä?ä¿¡æ?ï¼??å¦??ç§°ç?	å­??ä¸?
è¯´æ?ï¼?½¿?¨æ?æµ?¾®???ç¤¾äº¤ç½??è´???»å??¶ï?uidï¼?asswordä¸ºç©ºï¼??ä¼?eyï¼?????ä¸?id??assword ??¡¨ç¤ºä?è¯¥ç¤¾äº¤ç?ç»?´¦?·ç?å®???¨æ????å¯??????¨æ????å¯?????ä¿??ï¼??ä¸??ä½¿ç?ç¤¾äº¤ç½??è´???»å??¶ï??´æ?ä½¿ç?è¿??å¯¹ç??·å???????

ç¤ºä?
192.168.0.137/api/login
?¹æ?:POST
??? uid=3765 password=oe4hqqRx type=0
{
    "status": "0",
    "msg": "",
    "content": {
        "uid": "3765",
        "avatar":"/pic/user/default.jpg",
        "sex": "???äº?,
        "birthday": "''",
        "introduce": "''",
        "cellphone": "''",
        "weiboshare": "0",
        "renrenshare": "0",
        "focusnum": "0",
        "fansnum": "0",
        "username": "SYv6qP"
    }
}

	 */
	public MeishidarenUser toLogin(String username,String psw,
			String type) throws IOException, ParserException, NoConnectException{
		MeishidarenUser meishidarenUser = null;
		Bundle bundle = new Bundle();
		bundle.putString("username", username);
		bundle.putString("password", psw);
		bundle.putString("type", type);
		NetResult result =  this.doMeishidarenPost(LOGIN_PAGE, bundle);
		if(result.getErrorCode().equals("0")){
			
			meishidarenUser  = JsonParser.parseUserInfo(result.getRetContent()) ;
			
			
		}else{
			throw new ParserException(result.getErrorMessage() ) ;
		}
		return meishidarenUser;
	}
	
//	public NetResult login(String pNum, String psw) throws  NoConnectException, ParserException, java.io.IOException {
//		Bundle bundle = new Bundle();
//		bundle.putString("uid", pNum);
//		bundle.putString("password", psw);
//		bundle.putString("type", "0");
//		NetResult result =  this.doMeishidarenPost(LOGIN_PAGE, bundle);
//		if(!result.getErrorCode().equals("0")){
//			throw new ParserException(result.getErrorMessage() ) ;
//		}
//		return result ; 
//	}
	
	
	/*
	 * 
	 * 1.3	ç»??ç¤¾äº¤ç½??å¸??
  methodï¼?ind
å®?????è´??ä¸?¤¾äº¤ç?ç»?´¦?·ä??´ç?ç»????
è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
type	?»å?ç±»å?	1ï¼??æµ?¾®??´¦??
				2ï¼?ººäººè´¦??
				3:?¾è?å¾??
key	?´æ?å¯??	å­??ä¸?

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	???

	  
	  */
	public NetResult bindWeibo(String uid,String psw,String type,
			String key) throws IOException, ParserException, NoConnectException{
		Bundle bundle = new Bundle();
		bundle.putString("uid", uid);
		bundle.putString("type", type);
		bundle.putString("password", psw);
		bundle.putString("key", key);
		NetResult result =  this.doMeishidarenPost(BIND_WEIBO_PAGE, bundle);
		if(!result.getErrorCode().equals("0")){
			throw new ParserException(result.getErrorMessage() ) ;
		}
		
		return result;
	}
	
	public MeishidarenUser toSocailLogin(String type,String key) 
			throws IOException, ParserException, NoConnectException{
		MeishidarenUser meishidarenUser = null;
		Bundle bundle = new Bundle();
		bundle.putString("type", type);
		bundle.putString("key", key);
		NetResult result =  this.doMeishidarenPost(LOGIN_WEIBO_PAGE, bundle);
		if(result.getErrorCode().equals("0")){
			
			meishidarenUser  = JsonParser.parseUserInfo(result.getRetContent()) ;
			
			
		}else{
			throw new ParserException(result.getErrorMessage() ) ;
		}
		return meishidarenUser;
	}
	
	/*
	 *1.4	ä¿???¨æ?ä¿¡æ?
	 *method: modifyUserInfo
	 *è°?????
	 *???	??è¿???å¼?
	 *uid=value	?¨æ????ID	å­??ä¸?
	 *password=value	å¯??	å­??ä¸?
	 **=value	è¦?¿®?¹ç????	å­??ä¸?
	 *è¿??é¡¹è????
	 *è¿??é¡???è¿???å¼?
	 *status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
	 *msg	æ¶??	??????ï¼??å¤±è´¥???
	 *content	è¿?????	???
	 *è¯´æ?ï¼???¨å??°é???id ??assword ?????¡¹ï¼??äº????????
	 *?¶ä?????¨ä?è¡¨ç¤ºè¦?¿®?¹ç??????
	 */	
	
	public NetResult modifyUserInfo(String uid,String password,
			HashMap<String, String> userinfo) throws IOException, ParserException, NoConnectException{
		Bundle bundle = new Bundle();
		bundle.putString("uid", uid);
		bundle.putString("password", password);
		Iterator iter = userinfo.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String key = (String) entry.getKey(); 
		    String val = (String) entry.getValue(); 
		    bundle.putString(key, val);
		} 
		
		NetResult result =  this.doMeishidarenPost(MODIFY_USERINFO_PAGE, bundle);
		if(!result.getErrorCode().equals("0")){
			throw new ParserException(result.getErrorMessage() ) ;
		}
		return result;
	}
	/*
	 *   1.5  ä¿??å¯??
	 *   method:resetPassword
		???è¯´æ?:
	   	  uid : ?¨æ?????·ç?
		  password : ?§å???---ç¼??urlencode, utf8
		  newpassword : ?°å???---ç¼??urlencode, utf8
		è¿??ç»??:
		  {
			"status":0,    --???è¿??0
			"msg":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
			"content":"", --""
		  }
	 */
		public NetResult modifyPassword(String uid, String opsw, String npsw) throws NoConnectException, IOException, java.io.IOException, ParserException{
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
//			opsw = DigestAlgorithms.getMD5String(opsw);
//			npsw = DigestAlgorithms.getMD5String(npsw);
			bundle.putString("password", opsw);
			bundle.putString("newpassword", npsw);
			NetResult result =  this.doMeishidarenPost(MODIFY_PASSWORD_PAGE, bundle);
			if(!result.getErrorCode().equals("0")){
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return result ; 
		}
		/*
		 *   1.6	ä¿???¨æ???
		 *   method:resetPassword
			???è¯´æ?:
		   	  uid : ?¨æ????ID
			  password : å¯??----ç¼??urlencode, utf8
			  username : ?°ç??¨æ???-
			è¿??ç»??:
			  {
				"status":0,    --???è¿??0
				"msg":"", --errorCodeä¸?¸º0??è¿???·ä????ä¿¡æ?
				"content":"", --""
			  }
		 */
		public NetResult modifyUsername(String uid, String password, String username) throws NoConnectException, IOException, java.io.IOException, ParserException{
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("password", password);
				bundle.putString("newusername", username);
				NetResult result =  this.doMeishidarenPost(MODIFY_USERNAME_PAGE, bundle);
				if(!result.getErrorCode().equals("0")){
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return result ; 
			}
		/*
		 *   2.1	ä¸???¾ç?
Methodï¼?ploadPic

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
 InputStream	
 filename	 ?¾ç????

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.picid	?¾ç???d	å­??ä¸?
		 */
		
		
		
		public HashMap<String, String> uploadImage(String uid, String password, InputStream is,String filename) throws NoConnectException, IOException, java.io.IOException, ParserException{
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", password);
			String picid = "";
			HashMap<String, String> resultMap = new HashMap<String, String>();
			NetResult result =  this.doMeishidarenUpload(UPLOAD_PIC_PAGE, bundle,is,filename);
			if(result.getErrorCode().equals("0")){	
				picid  = JsonParser.parseUploadResult(result.getRetContent()) ;	
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			resultMap.put("picid", picid);
			return resultMap ; 
		}
		
		
		public NetResult uploadAvatar(String uid, String password, InputStream is,String filename) throws NoConnectException, IOException, java.io.IOException, ParserException{
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", password);
//			String picid = "";
//			HashMap<String, String> resultMap = new HashMap<String, String>();
			NetResult result =  this.doMeishidarenPic(UPLOAD_AVA_PAGE, bundle,is,filename);
//			if(result.getErrorCode().equals("0")){	
//				picid  = JsonParser.parseUploadResult(result.getRetContent()) ;	
//			}else{
//				throw new ParserException(result.getErrorMessage() ) ;
//			}
//			resultMap.put("status", "0");
			return result ; 
		}
		
		
		
		
		/*
		 *   2.2	???ç¾??å¾??
		 *   Methodï¼?oTweet

	è°?????
	???	??è¿???å¼?
picid	?¾ç?id	å­??ä¸²ï?å¤???¨å??????
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
text	??????	å­??ä¸?
type	ç±»å?	å­??ä¸²ï?å¹¿å???

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	???

		 */
		public NetResult toTweet(String uid, String password, String [] picid,
				String textContent,String type,String share,
				String ingredient,String staple,String accessories,String foodname,
				String dateline,String location,String latitude,String longitude) 
				throws NoConnectException, IOException, java.io.IOException, ParserException{
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("share", share);
				bundle.putString("password", password);
				String pics = picid[0];
				for (int i=1;i<picid.length;i++)  pics +=";"+picid[i];
				bundle.putString("picid", pics); 
				bundle.putString("description", textContent); 
				bundle.putString("type", type); 
				
				bundle.putString("ingredient", ingredient); 
				bundle.putString("staple", staple); 
				bundle.putString("accessories", accessories); 
				bundle.putString("foodname", foodname); 
				
				bundle.putString("dateline", dateline); 
				bundle.putString("location", location); 
				bundle.putString("latitude", latitude); 
				bundle.putString("longitude", longitude); 
				
				NetResult result =  this.doMeishidarenPost(TWEET_PAGE, bundle);
				if(!result.getErrorCode().equals("0")){
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return result ; 
		}
		/*
		 *   2.3	????¶è?
Methodï¼?ddFavorite

è°?????
???	??è¿???å¼?
itemid	ç¾??itemid	å­??ä¸?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	???


		 */
		public NetResult addFavorite(String uid, String password, String tweetid) throws NoConnectException, IOException, java.io.IOException, ParserException{
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("password", password);
				bundle.putString("tweetid", tweetid); 
				NetResult result =  this.doMeishidarenPost(TWEET_PAGE, bundle);
				if(!result.getErrorCode().equals("0")){
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return result ; 
		}
		
		
		/*
		 *   2.4	è¿???¶è???¡¨
Methodï¼?etFavorites

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.list	?¶è???¡¨	JSONArray
content.list.text	ç¾??Item???å­??è¿?å­??ä¸?
content.list.pic	ç¾??Itemå¯¹å?????????url

		 */
				public ArrayList<HashMap<String, String>> getFavorites(String uid,String pagenum) throws IOException, ParserException, NoConnectException{
					ArrayList<HashMap<String, String>>  tweets = null;
					Bundle bundle = new Bundle();
					bundle.putString("uid", uid);
					bundle.putString("password", pagenum);
					NetResult result =  this.doGetFavoritesPost(GET_FAV_PAGE, bundle);
					
					if(result.getErrorCode().equals("0")){
						
						tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
						
						
					}else{
						throw new ParserException(result.getErrorMessage() ) ;
					}
					return tweets;
				}
				
				
				public NetResult doGetFavoritesPost(String page, Bundle bundle) throws  NoConnectException, java.io.IOException, ParserException{
					StringBuilder sb = new StringBuilder(Meishidaren_BASIC_URL);
					sb.append(page);
					String ret = null;
					NetResult result = new NetResult() ;
					ret = NetUtils.openUrl(sb.toString(), "post", bundle, context);
//					Log.i(TAG, "ret = "+ret);
					Log.i(page, result.toString());
					result = JsonParser.parseNetResult(ret);
					if (result== null ) Log.e(TAG,"ERROR! result is null!");
					else Log.e(TAG,"result error message:"+result.getErrorMessage()+",result error code:"+result.getErrorCode());
					Log.i(TAG, "result = "+result.getRetContent());
					
					return result ;
				}

				/*
				 *   3.1.	???æ³?
				 *   Methodï¼?ddFocus
					???è¯´æ?:
				   	  uid : ?¨æ????ID
					  password : ?§å???---ç¼??urlencode, utf8
					  focusuid : è¢??æ³¨ç??·ç?ID
					è¿??ç»??:
					  {
						"status":0,    --???è¿??0
						"msg":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
						"content":"", --""
					  }
				 */
					public NetResult addFocus(String uid, String psw, String focusuid) throws NoConnectException, IOException, java.io.IOException, ParserException{
						Bundle bundle = new Bundle();
						bundle.putString("uid", uid);
						bundle.putString("password",  psw);
						bundle.putString("focusuid", focusuid);
						NetResult result =  this.doMeishidarenPost(ADD_FOCUS_PAGE, bundle);
						if(!result.getErrorCode().equals("0")){
							throw new ParserException(result.getErrorMessage() ) ;
						}
						return result ; 
					}
					
					
					public NetResult cancleFocus(String uid, String psw, String focusuid) throws NoConnectException, IOException, java.io.IOException, ParserException{
						Bundle bundle = new Bundle();
						bundle.putString("uid", uid);
						bundle.putString("password",  psw);
						bundle.putString("focusuid", focusuid);
						NetResult result =  this.doMeishidarenPost("cancleFocus", bundle);
						if(!result.getErrorCode().equals("0")){
							throw new ParserException(result.getErrorMessage() ) ;
						}
						return result ; 
					}
				
					/*
					 *   3.2.	è¿???¨æ?ä¿¡æ?
Methodï¼?etUserInfo

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
userid	?¥è??????D	å­??ä¸?

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.username	?µç§°	å­??ä¸?
content.gender	?§å?	å­??ä¸?
content.intro	???ä»??	å­??ä¸?
content.focusnum	?³æ³¨??å­??ä¸?
content.followernum	ç²????å­??ä¸?
content.tweetnum	ç¾??å¾????å­??ä¸?
content.*	?¶ä?	å­??ä¸?



					 */		
					
					public MeishidarenUser getUserInfo(String uid,String psw,
							String userid) throws IOException, ParserException, NoConnectException{
						MeishidarenUser meishidarenUser = null;
						Bundle bundle = new Bundle();
						bundle.putString("uid", uid);
						bundle.putString("password", psw);
						bundle.putString("userid", userid);
						NetResult result =  this.doMeishidarenPost(GET_USERINFO_PAGE, bundle);
						if(result.getErrorCode().equals("0")){
							
							meishidarenUser  = JsonParser.parseUserInfo(result.getRetContent()) ;
							
							
						}else{
							throw new ParserException(result.getErrorMessage() ) ;
						}
						return meishidarenUser;
					}
					/*
					 *   3.3.	è¿???¨æ?ç¾??å¾??
Methodï¼?etUserTweets

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
userid	?³æ³¨?¨æ????è¯??	å­??ä¸²ï?ä¼????ï¼?¡¨??????å·±å?æ³¨ç??¨æ????ç¾??ï¼???¶ä?idè¡¨æ?è¿??è¯?d????????
num	é¡µæ?	?°ç? é»??20
tweetid	ç¾??å¾??id	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªå¾??id(ç¬??æ¬¡å?ä¸?ï¼?
	updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid
updateway	?´æ??¹å?	'more' ??'refresh'

è¿??é¡¹è????
		
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	 JSONArray
content.usename	ç¾??item???????·å?	
content.uid	ç¾??item?????d	å­??ä¸?
content.userpic	ç¾??item?????¤´?????url
content.tweetid	ç¾??id	å­??ä¸?
content.description	ç¾??Item???å­??è¿?å­??ä¸?
content.picurl	ç¾??Itemå¯¹å?????????url
content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
content.likenum	ç¾??Item???å­??ç¾??	å­??ä¸?
content.dateline	ç¾??Item????¶é?	20120330172800



					 */
		public ArrayList<HashMap<String, String>> getUserTweets(String uid,String pwd,String userid,String tweetid,String updateway,String type) throws IOException, ParserException, NoConnectException{
			ArrayList<HashMap<String, String>>  tweets = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("num", "500");
			bundle.putString("password", pwd);
			bundle.putString("userid", userid);
			bundle.putString("tweetid", tweetid);
			bundle.putString("updateway", updateway);
			bundle.putString("type", type);
			NetResult result =  this.doMeishidarenListPost(GET_TWEET_PAGE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return tweets;
		}
		
	/*
	 * 4.2.	è¿?????ç¾??å¾??
Methodï¼?etTopTweets

è°?????
???	??è¿???å¼?
uid	??·±???è¯??	å­??ä¸?
password	å¯??	å­??ä¸?
num	?°ç?	?°é?ï¼??è®?0

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.usename	ç¾??item???????·å?	
content.uid	ç¾??item?????d	å­??ä¸?
content.userpic	ç¾??item?????¤´?????url
content.tweetid	ç¾??id	å­??ä¸?
content.type	ç±»å?	å­??ä¸²ï?ä¾¿å?ç±»å? 0 1 2 3 
content.ingredient	ä¸»æ?	å­??ä¸?
content.staple
ä¸»é?	å­??ä¸?
content.accessories	è¾??	å­??ä¸?
content.location	ä½?½®	å­??ä¸?
content.latitude	ç»?º¦	å­??ä¸?
content .longitude	çº?º¦	å­??ä¸?
content.foodname	???	å­??ä¸?
content.description	ç¾??Item???å­??è¿?å­??ä¸?
content.picurl	ç¾??Itemå¯¹å?????????url
content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
content.goodnum	ç¾??Item???å­??ç¾??	å­??ä¸?
content.dateline	ç¾??Item????¶é?	20120330172800
	
	 */
		public ArrayList<HashMap<String, String>> getTopTweets(String uid,String pwd,String userid,String tweetid,String updateway,String num) throws IOException, ParserException, NoConnectException{
			ArrayList<HashMap<String, String>>  tweets = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("num", num);
			bundle.putString("password", pwd);
			bundle.putString("userid", userid);
			bundle.putString("tweetid", tweetid);
			bundle.putString("updateway", updateway);
//			bundle.putString("type", type);
			NetResult result =  this.doMeishidarenListPost(GET_TOP_PAGE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return tweets;
		}
		
		/*
		 * 3.18.	?????????ç¾??
Methodï¼?etMyShares

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
updateway	?´æ??¹å?	å­??ä¸²ï?'more' ??'refresh'
num	?·å??°é?	æ¯??è¿??20??
id	æ¯???·å?????????d	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªæ¶??id(ç¬??æ¬¡å?ä¸?ï¼?
updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONArray
content.id	id	å­??ä¸?
content.dateline	æ¶???????å­??ä¸?
content.commentcontent	æ¶?????å®?å­??ä¸²ï???????è®ºç??¶å????ä¸ºç©º
Content.picurl	?¨è?ç¾????????é£????rl	????????????èµ???¶å????ä¸ºç©º
Content.foodname	ç¾?????ç§?
content. sharetonames	è¢??????¨æ?	å­??ä¸²ï??¨å??·å???
content.tweetid	ç¾??å¾????d	å­??ä¸?
content.description	?¨è?ç¾?????è¿?å­??ä¸²ï?

		 */
		
		public ArrayList<HashMap<String, String>> getMyShares(String uid,String pwd,String tweetid,String updateway) throws IOException, ParserException, NoConnectException{
			ArrayList<HashMap<String, String>>  tweets = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("num", "500");
			bundle.putString("password", pwd);
			bundle.putString("id", tweetid);
			bundle.putString("updateway", updateway);
//			bundle.putString("type", type);
			NetResult result =  this.doMeishidarenListPost(GET_MY_SHARE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return tweets;
		}
		
//		3.4.	?¹æ?å¾??idè¿???¸å?ç¾??å¾??
//		Methodï¼?howOneTweet
//
//		è°?????
//		???	??è¿???å¼?
//		uid	?¨æ????ID	å­??ä¸?
//		password	å¯??	å­??ä¸?
//		tweetid	ç¾??å¾??id	
//				
//
//		è¿??é¡¹è????
//				
//		status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//		msg	æ¶??	??????ï¼??å¤±è´¥???
//		content	è¿?????	 JSONArray
//		content.usename	ç¾??item???????·å?	
//		content.uid	ç¾??item?????d	å­??ä¸?
//		content.userpic	ç¾??item?????¤´?????url
//		content.tweetid	ç¾??id	å­??ä¸?
//		content.type	ç±»å?	å­??ä¸²ï?ä¾¿å?ç±»å? 0 1 2 3 
//		content.ingredient	ä¸»æ?	å­??ä¸?
//		content.staple
//		ä¸»é?	å­??ä¸?
//		content.accessories	è¾??	å­??ä¸?
//		content.foodname	???	å­??ä¸?
//		content.description	ç¾??Item???å­??è¿?å­??ä¸?
//		content.picurl	ç¾??Itemå¯¹å?????????url
//		content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
//		content.likenum	ç¾??Item???å­??ç¾??	å­??ä¸?
//		content.dateline	ç¾??Item????¶é?	20120330172800
//		content.islike	å½???¨æ????èµ??	0??1 ??

		public ArrayList<HashMap<String, String>> getOneTweets(String uid,String pwd,String tweetid) throws IOException, ParserException, NoConnectException{
			ArrayList<HashMap<String, String>>  tweets = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
			bundle.putString("tweetid", tweetid);
			NetResult result =  this.doMeishidarenListPost(GET_ONE_TWEET, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				tweets  = JsonParser.parseOneTweets(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return tweets;
		}
		
		
		public ArrayList<HashMap<String, String>> getUserGoodTweets(String uid,String pwd,String userid,String tweetid,String updateway,String type) throws IOException, ParserException, NoConnectException{
			ArrayList<HashMap<String, String>>  tweets = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
			bundle.putString("userid", userid);
			bundle.putString("tweetid", tweetid);
			bundle.putString("updateway", updateway);
			bundle.putString("type", type);
			NetResult result =  this.doMeishidarenListPost(GET_GOOD_TWEET_PAGE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return tweets;
		}
		
	/*	
	 * 3.4.	???ç²????¡¨
	 * Methodï¼?etUserFollowers
	 * 
	 * è°?????
		???	??è¿???å¼?
		uid	?¨æ????ID	å­??ä¸?
		password	å¯??	å­??ä¸?
		who	???ä¸??è¡¨ç???d	
		num	æ¯??????°ç?	é»??20
		id	æ¯??ç²????¡¨?????????ä¸?d	ç¬??æ¬¡å???

		è¿??é¡¹è????
		è¿??é¡???è¿???å¼?
		status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
		msg	æ¶??	??????ï¼??å¤±è´¥???
		content	è¿?????	 JSONArray
		content.uid	ç²????d	å­??ä¸?
		content. username	ç²??????·å?	å­??ä¸?
		content. avatar	ç²????¤´??url	
		content. tweet	ç²?????è¿???¡å¾®???å­?å­??ä¸?

	 */
	public ArrayList<UserSimple> getUserFollowers(String uid,String pwd,String who,String num,String lastuid) throws IOException, ParserException, NoConnectException{
		ArrayList<UserSimple>  followers = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
			bundle.putString("who", who);
			bundle.putString("num", num);
			bundle.putString("id", lastuid);
			
			NetResult result =  this.doMeishidarenListPost(GET_FOLLOWERS_PAGE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				followers  = JsonParser.parseUserSimple(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return followers;
		}	
	
	/*
	 * 3.4.	è¿??????¨æ?ä¿¡æ?
Methodï¼?etTopUsers

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
num	?°é?	

è¿??é¡¹è????
  
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.username	?µç§°	å­??ä¸?
Content.isvip	Vip?¨æ?	0 ä¸?? 1 ??
content.isresetpassword	?????½®ç§??	0 ä»£è¡¨æ²¡æ?ï¼?ä»£è¡¨??½®è¿?
content.email	???	å­??ä¸?
content.sex	?§å?	å­??ä¸?
content.introduce	???ä»??	å­??ä¸?
content.birthday	???	å­??ä¸?
content.constellation	??º§	å­??ä¸?
content.area	?°å?	å­??ä¸?
content.sina	?°æµªå¾??ä¸»é¡µ?°å?	å­??ä¸?
content.renren	äººäººä¸»é¡µ?°å?	å­??ä¸?
content.weixin	å¾?¿¡å¸??	å­??ä¸?
content.cellphone	????·ç?	å­??ä¸?
content.focusnum	?³æ³¨??å­??ä¸?
content.fansrnum	ç²????å­??ä¸?
content.tweetnum	ç¾??å¾????å­??ä¸?
content.uid	?¨æ?uid	å­??ä¸?
Content.avatar	?¨æ?å¤´å?	url
content.weiboshare	å¾????º«	0ä»£è¡¨???å®????º«ï¼?ä»£è¡¨ç»??ä¸??äº??2ä»£è¡¨ç»??ä¸??ç½??äº?
Content.isfocus	??????æ³¨ä?	0ä»£è¡¨???æ³??1ä»£è¡¨?³æ³¨,2ä»£è¡¨???å·±ï?ä¸??è¯¥æ?ç¤ºæ????æ³?

	 */
	public ArrayList<UserSimple> getTopUsers(String uid,String pwd,String who,String num,String lastuid) throws IOException, ParserException, NoConnectException{
		ArrayList<UserSimple>  followers = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
//			bundle.putString("who", who);
			bundle.putString("num", num);
//			bundle.putString("id", lastuid);
			
			NetResult result =  this.doMeishidarenListPost(GET_TOP_USERS, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				followers  = JsonParser.parseUserSimple(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return followers;
		}
		
	/*	
	 * 3.5.	????³æ³¨??¡¨
	 * Methodï¼?etUserFocus
	 * 
	 * è°?????
???	??è¿???å¼?
uid	?¨æ?id	å­??ä¸?
password	å¯??	å­??ä¸?
who	???æ³¨å?è¡¨ç???d	
num	æ¯??????°ç?	é»??20
id	æ¯???³æ³¨??¡¨?????????ä¸?d	ç¬??æ¬¡å??¼æ?0

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONArray
content. uid	ç²????d	å­??ä¸?
content. avatar	ç²????¤´??url
content. username	ç²??????·å?	å­??ä¸?
content.dateline	?³æ³¨?¶é?	
content.id	?³æ³¨id	
content. tweet	ç²?????è¿???¡å¾®???å­?å­??ä¸?


	 */
	public ArrayList<UserSimple> getUserFocus(String uid,String pwd,String who,String num,String lastuid) throws IOException, ParserException, NoConnectException{
		ArrayList<UserSimple>  followers = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
			bundle.putString("who", who);
			bundle.putString("num", num);
			bundle.putString("id", lastuid);
			
			NetResult result =  this.doMeishidarenListPost(GET_FOCUSERS_PAGE, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				followers  = JsonParser.parseUserSimple(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return followers;
		}	
	
	
//	1.5	?·å?ç¤¾äº¤å¥½å?
//	methodï¼?ocialFriends
//
//	è°?????
//	???	??è¿???å¼?
//	username	?¨æ???å­??ä¸?
//	password	å¯??	å­??ä¸?
//
//	è¿??é¡¹è????
//	è¿??é¡???è¿???å¼?
//	status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//	msg	æ¶??	??????ï¼??å¤±è´¥???
//	content	è¿?????	JSONObject
//	content.uid	ç¤¾äº¤è´??id	å­??ä¸?
//	content.username	ç¤¾äº¤è´???¨æ???å­??ä¸?
//	Content.avatar	ç¤¾äº¤ç½???????å¤?³¨ï¼??ä¸????rlä¸?????ï¼????¤¾äº¤ç?ç»????
	public ArrayList<UserSimple> getWeiboFocus(String uid,String pwd,String who,String num,String lastuid) throws IOException, ParserException, NoConnectException{
		ArrayList<UserSimple>  followers = null;
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password", pwd);
//			bundle.putString("who", who);
//			bundle.putString("num", num);
//			bundle.putString("id", lastuid);
			
			NetResult result =  this.doMeishidarenListPost(GET_WEIBO_FRI, bundle);
			
			if(result.getErrorCode().equals("0")){
				
				followers  = JsonParser.parseUserSimple(result.getRetContent()) ;
				
				
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return followers;
		}
	
	/*
	 *   3.6.	è¯??
	 *   Methodï¼?oComment
	 *    
		???è¯´æ?:
	   	  uid : ?¨æ????ID
	   	  password	å¯??	å­??ä¸?
		  comment	è¯?????	å­??ä¸?
		  tweetid	è¯?????é£?¾®??d	å­??ä¸?
		è¿??ç»??:
		  {
			"status":0,    --???è¿??0
			"msg":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
			"content":"", --""
		  }
	 */
		public NetResult doComment(String uid, String psw, String comment,String tweetid) throws NoConnectException, IOException, java.io.IOException, ParserException{
			Bundle bundle = new Bundle();
			bundle.putString("uid", uid);
			bundle.putString("password",  psw);
			bundle.putString("comment", comment);
			bundle.putString("tweetid", tweetid);
			NetResult result =  this.doMeishidarenPost(DO_COMMENT_PAGE, bundle);
			if(!result.getErrorCode().equals("0")){
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return result ; 
		}
		
		/*	
		 * 3.7.	?·å????ç¾??å¾??è¯????¡¨
Methodï¼?etComments

è°?????
???	??è¿???å¼?
uid	??·±??d	å­??ä¸?
password	å¯??	å­??ä¸?
tweetid	ç¾??å¾??id	å­??ä¸?
num	?·å??°é?	æ¯??è¿??20??
commentid	æ¯???·å????????¡è?è®?ç¬??æ¬¡è?æ±??è®¤ä¸º0

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONArray
Content.username	è¯???¨æ???
content. uid	è¯???¨æ?id	å­??ä¸?
content. userpic	è¯????¤´?????url
content. commentid	è¯????d	å­??ä¸?
content. comment	è¯?????å®?å­??ä¸?
Content.dateline	è¯???¶é?	



		 */
		public ArrayList<Comment> getComments(String uid,String pwd,String tweetid,String num,String lastcommentid) throws IOException, ParserException, NoConnectException{
			ArrayList<Comment>  comments = null;
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("password", pwd);
				bundle.putString("tweetid", tweetid);
				bundle.putString("num", num);
				bundle.putString("commentid", lastcommentid);
				
				NetResult result =  this.doMeishidarenListPost(GET_COMMENTS_PAGE, bundle);
				
				if(result.getErrorCode().equals("0")){
					
					comments  = JsonParser.parseComments(result.getRetContent()) ;
					
					
				}else{
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return comments;
			}	
		/*
		 *   3.8.	èµ?
		 *   Methodï¼?oGood
		 *    
			???è¯´æ?:
		   	  uid : ?¨æ????ID
		   	  password	å¯??	å­??ä¸?
			  tweetid	è¯?????é£?¾®??d	å­??ä¸?
			è¿??ç»??:
			  {
				"status":0,    --???è¿??0
				"msg":"", --errorCode ä¸?¸º0??è¿???·ä????ä¿¡æ?
				"content":"", --""
			  }
		 */
			public NetResult doLike(String uid, String psw, String tweetid) throws NoConnectException, IOException, java.io.IOException, ParserException{
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("password",  psw);
				bundle.putString("tweetid", tweetid);
				NetResult result =  this.doMeishidarenPost(DO_LIKE_PAGE, bundle);
				if(!result.getErrorCode().equals("0")&&!result.getErrorCode().equals("1")){
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return result ; 
			}
			
			
			public NetResult cancelLike(String uid, String psw, String tweetid) throws NoConnectException, IOException, java.io.IOException, ParserException{
				Bundle bundle = new Bundle();
				bundle.putString("uid", uid);
				bundle.putString("password",  psw);
				bundle.putString("tweetid", tweetid);
				NetResult result =  this.doMeishidarenPost(CANCEL_LIKE_PAGE, bundle);
				if(!result.getErrorCode().equals("0")&&!result.getErrorCode().equals("1")){
					throw new ParserException(result.getErrorMessage() ) ;
				}
				return result ; 
			}
			
			/*	3.9.	???èµ????¡¨
			 *è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
tweetid	ç¾??å¾??id	å­??ä¸?
num	?·å??°é?	æ¯??è¿??20??
goodtid	æ¯???·å????????¡è?	ç¬??æ¬¡è?æ±??è®¤ä¸º0

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONArray
content.goodid	èµ??id	å­??ä¸?
content. uid	?????å­??ä¸?
Content.username	èµ???¨æ???
content. userpic	èµ??å¤´å??°å?	url
Content.dateline	èµ???¶é?	


			 */
			public ArrayList<Comment> getLikes(String uid,String pwd,String tweetid,String num,String lastcommentid) throws IOException, ParserException, NoConnectException{
				ArrayList<Comment>  comments = null;
					Bundle bundle = new Bundle();
					bundle.putString("uid", uid);
					bundle.putString("password", pwd);
					bundle.putString("tweetid", tweetid);
					bundle.putString("num", num);
					bundle.putString("commentid", lastcommentid);
					
					NetResult result =  this.doMeishidarenListPost(GET_LIKES_PAGE, bundle);
					
					if(result.getErrorCode().equals("0")){
						
						comments  = JsonParser.parseLikes(result.getRetContent()) ;
						
						
					}else{
						throw new ParserException(result.getErrorMessage() ) ;
					}
					return comments;
				}	
			/*
			 *   3.10.	???æ¶?????è¡?
Methodï¼?etMessages

è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
updateway	?´æ??¹å?	å­??ä¸²ï?'more' ??'refresh'
num	?·å??°é?	æ¯??è¿??20??
msgid	æ¯???·å????????¡è?	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªæ¶??id(ç¬??æ¬¡å?ä¸?ï¼?
updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONArray
content.msgid	æ¶????d	å­??ä¸?
content.fromuid	æ¶???????å­??ä¸?
content.fromusername	???????¨æ???å­??ä¸?
content.fromuserpic	??????å¤´å??°å?	url
content.dateline	æ¶???????å­??ä¸?
content.commentcontent	æ¶?????å®?å­??ä¸²ï?èµ???¶å?ä¸ºç©º
content.msgtype	æ¶?????ç±?å­??ä¸²ï?0ä¸ºè?è®ºï?1ä¸ºè?
content.tweetid	ç¾??å¾????d	å­??ä¸?
content.tweetdescription	ç¾??å¾???????¿¡??å­??ä¸?


			 */
public ArrayList<TweetMsg> getMessages(String uid,String pwd,String msgid,String updateway) throws IOException, ParserException, NoConnectException{
	
	ArrayList<TweetMsg>  tweetMsgs = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("msgid", msgid);
	bundle.putString("num", "200");
	bundle.putString("updateway", updateway);
	NetResult result =  this.doMeishidarenListPost(GET_MSG_PAGE, bundle);
	
	if(result.getErrorCode().equals("0")){
		
		tweetMsgs  = JsonParser.parseMessage(result.getRetContent()) ;
		
		
	}else{
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return tweetMsgs;
}		
			/*
			 *   4.1.	è¿????±»ç¾??å¾??
Methodï¼?etCategoryTweets

è°?????
???	??è¿???å¼?
uid	??·±???è¯??	å­??ä¸?
password	å¯??	å­??ä¸?
type	??±»	å­??ä¸²ï?ç¾??å¾??ç±»å?
num	?°ç?	?°é?ï¼??è®?0
tweetid	ç¾??å¾??id	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªå¾??id(ç¬??æ¬¡å?ä¸?ï¼?
					updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid
updateway	?´æ??¹å?	'more' ??'refresh' 

è¿??é¡¹è????
è¿??é¡???è¿???å¼?
status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
content	è¿?????	JSONObject
content.usename	ç¾??item???????·å?	
content.uid	ç¾??item?????d	å­??ä¸?
content.userpic	ç¾??item?????¤´?????url
content.tweetid	ç¾??id	å­??ä¸?
content.description	ç¾??Item???å­??è¿?å­??ä¸?
content.picurl	ç¾??Itemå¯¹å?????????url
content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
content.likenum	ç¾??Item???å­??ç¾??	å­??ä¸?
content.dateline	ç¾??Item????¶é?	20120330172800

			 */
public ArrayList<HashMap<String, String>> getCategoryTweets(String uid,String pwd,String type, String userid,String tweetid,String updateway) throws IOException, ParserException, NoConnectException{
	ArrayList<HashMap<String, String>>  tweets = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("userid", userid);
	bundle.putString("type", type);
	bundle.putString("tweetid", tweetid);
	bundle.putString("updateway", updateway);
	NetResult result =  this.doMeishidarenListPost(GET_CAT_TWEET_PAGE, bundle);
	
	if(result.getErrorCode().equals("0")){
		
		tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
		
		
	}else{
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return tweets;
}		
		
/*
 *  3.19.	??´¢
Methodï¼?oSearch


è°?????
???	??è¿???å¼?
uid	?¨æ????ID	å­??ä¸?
password	å¯??	å­??ä¸?
keyword	??´¢?³é?è¯?å­??ä¸?
pagenum	ç¬??é¡?ä»?å¼??

è¿??é¡¹è????

status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
msg	æ¶??	??????ï¼??å¤±è´¥???
totalpagenum	?»é¡µ???°å?
totalnum	ç»???»æ?	?°å?
content	è¿?????	 JSONArray
content.usename	ç¾??item???????·å?	
content.uid	ç¾??item?????d	å­??ä¸?
content.userpic	ç¾??item?????¤´?????url
content.tweetid	ç¾??id	å­??ä¸?
content.description	ç¾??Item???å­??è¿?å­??ä¸?
content.picurl	ç¾??Itemå¯¹å?????????url
content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
content.likenum	ç¾??Item???å­??ç¾??	å­??ä¸?
content.dateline	ç¾??Item????¶é?	20120330172800 
content.islike	å½???¨æ????èµ??	0??1 ??
 */

public ArrayList<HashMap<String, String>> doSearchFood(String uid,String pwd,String type,String keyword,String pagenum,String userid,String num) 
		throws IOException, ParserException, NoConnectException{
	
	ArrayList<HashMap<String, String>>  tweets = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("keyword", keyword);
	bundle.putString("tweetid", pagenum);
	bundle.putString("type", type);
	bundle.putString("userid",userid);
	bundle.putString("num", num);
	
	HashMap<String,String> result =  this.doSearchPost(DO_SEARCH, bundle);
//	ShortCut.totalnum = Integer.parseInt(result.get("totalnum"));
//	ShortCut.totalpagenum = Integer.parseInt(result.get("totalpagenum"));
	System.out.println("?³é?è¯??++++++++++++++++="+keyword);
	if(result.get("status").equals("0")){
		  tweets  = JsonParser.parseSearchTweets(result.get("content")) ;
	}else{
		throw new ParserException(result.get("msg") ) ;
	}
	return tweets;
}

public ArrayList<UserSimple> doSearchUser(String uid,String pwd,String type,String keyword,String pagenum,String userid,String num) 
		throws IOException, ParserException, NoConnectException{
	
	ArrayList<UserSimple>  users = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("keyword", keyword);
	bundle.putString("tweetid", pagenum);
	bundle.putString("type", type);
	bundle.putString("userid",userid);
	bundle.putString("num", num);
	
	HashMap<String,String> result =  this.doSearchPost(DO_SEARCH, bundle);
//	ShortCut.totalnum = Integer.parseInt(result.get("totalnum"));
//	ShortCut.totalpagenum = Integer.parseInt(result.get("totalpagenum"));
	System.out.println("?³é?è¯??++++++++++++++++="+keyword);
	if(result.get("status").equals("0")){
		  users  = JsonParser.parseSearchUsers(result.get("content")) ;
	}else{
		throw new ParserException(result.get("msg") ) ;
	}
	return users;
}

//3.11.	?????ººèµ??ç¾????¡¨
//Methodï¼?etUserGoodSweets
//
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//userid	?¨æ?id	å¦????????å·±è????è¡??è¿?¸ª??»¥ä¸ºç©º
//num	?·å??°é?	æ¯??è¿??20??
//		
//tweetid	ç¾??å¾??id	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªå¾??id(ç¬??æ¬¡å?ä¸?ï¼?
//updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid
//		
//updateway	?´æ??¹å?	'more' ??'refresh'
//		
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	JSONArray
//content.usename	ç¾??item???????·å?	
//content.uid	ç¾??item?????d	å­??ä¸?
//content.userpic	ç¾??item?????¤´?????url
//content.tweetid	ç¾??id	å­??ä¸?
//content.type	ç±»å?	å­??ä¸²ï?ä¾¿å?ç±»å? 0 1 2 3 
//content.ingredient	ä¸»æ?	å­??ä¸?
//content.staple
//ä¸»é?	å­??ä¸?
//content.accessories	è¾??	å­??ä¸?
//content.foodname	???	å­??ä¸?
//content.description	ç¾??Item???å­??è¿?å­??ä¸?
//content.picurl	ç¾??Itemå¯¹å?????????url
//content.commentnum	ç¾??Itemå¯¹å????è®ºæ?	å­??ä¸?
//content.likenum	ç¾??Item???å­??ç¾??	å­??ä¸?
//content.dateline	ç¾??Item????¶é?	20120330172800
//content.islike	å½???¨æ????èµ??	0??1 ??

public ArrayList<HashMap<String, String>> getUserGoodSweets(String uid,String pwd,String userid,String tweetid,String updateway,String num) throws IOException, ParserException, NoConnectException{
	ArrayList<HashMap<String, String>>  tweets = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("userid", userid);
	bundle.putString("tweetid", tweetid);
	bundle.putString("updateway", updateway);
	bundle.putString("num", "20");
	NetResult result =  this.doMeishidarenListPost(GET_USER_GOOD, bundle);
	
	if(result.getErrorCode().equals("0")){
		tweets  = JsonParser.parseUserTweets(result.getRetContent()) ;
	}else{
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return tweets;
}

//3.12.	???æ¶?????è¡?
//Methodï¼?etMessages
//
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//updateway	?´æ??¹å?	å­??ä¸²ï?'more' ??'refresh'
//num	?·å??°é?	æ¯??è¿??20??
//msgid	æ¯???·å????????¡è?	updateway='more'ä¼?????æ¬¡è?æ±?????ä¸?¸ªæ¶??id(ç¬??æ¬¡å?ä¸?ï¼?
//updateway='refresh'ä¼??ä¸??è¯·æ????ä¸?¸ªid
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	JSONArray
//content.msgid	æ¶????d	å­??ä¸?
//content.fromuid	æ¶???????å­??ä¸?
//content.fromusername	???????¨æ???å­??ä¸?
//content.fromuserpic	??????å¤´å??°å?	url
//content.dateline	æ¶???????å­??ä¸?
//content.commentcontent	æ¶?????å®?å­??ä¸²ï???????è®ºç??¶å????ä¸ºç©ºèµ???¶å?ä¸ºç©º
//Content.tweetpicurl	?¨è?ç¾????????é£????rl	????????????èµ???¶å????ä¸ºç©º
//Content.foodname	ç¾?????ç§?
//content.msgtype	æ¶?????ç±?å­??ä¸²ï?0ä¸ºè?è®ºï?1ä¸ºè?ï¼?ä¸ºå?æ³?3ä¸ºæ???
//content.tweetid	ç¾??å¾????d	å­??ä¸?
//content.sharetweetdescription	?¨è?ç¾?????è¿°ç?é£?¾®??????ä¿¡æ?	å­??ä¸²ï?????????????ä¸ºç©º
public ArrayList<TweetMsg> getMessages(String uid,String pwd,String userid,String num,String updateway,String msgid) throws IOException, ParserException, NoConnectException{
	ArrayList<TweetMsg>  message = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("num", num);
	bundle.putString("updateway", updateway);
	bundle.putString("msgid", msgid);
	NetResult result =  this.doMeishidarenListPost(GET_MESSAGE, bundle);
	
	if(result.getErrorCode().equals("0")){
		
		message  = JsonParser.parseMessage(result.getRetContent()) ;
		
		
	}else{
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return message;
}

//3.16.	?´æ?æ¶????¡¨
//Methodï¼?eadMessages
//
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//msgid	??¤§msgid	
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	JSONArray
public NetResult ReadMessages(String uid,String pwd,String msgid) throws IOException, ParserException, NoConnectException{
	ArrayList<TweetMsg>  message = null;
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password", pwd);
	bundle.putString("msgid", msgid);
	NetResult result =  this.doMeishidarenListPost("readMessages", bundle);
	
	if(!result.getErrorCode().equals("0")){
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return result ; 
}


//3.13.	?¨è???º«ç¾??å¾??
//Methodï¼?hareTweet
//
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//descriptioncomment	?¨è???¿°è¯?????	å­??ä¸?
//tweetid	è¯?????é£?¾®??d	å­??ä¸?
//Atuserids	è¢?äººæ??°å¥½???uid	å­??ä¸²ï?å¤?¸ª?¨å??????
//weibouserids	è¢???¾®??¥½???uid	å­??ä¸²ï?å¤?¸ª?¨å??????
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	???
public NetResult shareTweet(String uid, String psw,String descriptioncomment, String tweetid,String Atuserids,String userNames,String weibouserids,String weibouserNames) throws NoConnectException, IOException, java.io.IOException, ParserException{
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password",  psw);
	bundle.putString("tweetid", tweetid);
	
	bundle.putString("description", descriptioncomment);
	bundle.putString("userids", Atuserids);
	bundle.putString("usernames", userNames);
	bundle.putString("weiboids", weibouserids);
	bundle.putString("weibonames", weibouserNames);
	
	NetResult result =  this.doMeishidarenPost(SHARE_TWEET, bundle);
	if(!result.getErrorCode().equals("0")){
		throw new ParserException(result.getErrorMessage() ) ;
	}
	return result ; 
}

//3.20.	??´¢???
//Methodï¼?etTopSearchWords
//
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//num	?°ç?  ??????????¨æ?????¨ç?é£????»¥?»å??????????ä¸?*num,numé»??ä¸?0
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	???
public  String[] getTopSearchWords(String uid,String password,String num) throws IOException, ParserException, NoConnectException{
	
	//TODO:do get
	Bundle bundle = new Bundle();
	bundle.putString("uid", uid);
	bundle.putString("password",  password);
	bundle.putString("num", num);
	NetResult result =  this.doTopSearchWordsGet(GET_TOP_SEARCH_WORDS, bundle);
    String[] str = null;

		str =JsonParser.parseTopSearchWords(result.getRetContent()) ;
	Log.i("TopSearchWords:",str.toString() );
	return str ; 
}


		/*
		 * 5.1.	æ£???´æ??¥å? 
		 * Methodï¼?ppupdate
		 *  
	???:
	  type: è®¾å?ç±»å?ï¼??é¡»å??????????ä¹??ç±»å???iphone,android]
	  version: è®¾å?appå½???????
	  è¿??ç»??
	{
    	"status": "0",
    	"msg": "???",
    	"content": {
        	"update": 1,                         // ????´æ?ï¼???°ï?0ä¸ºä??´æ?ï¼?ä¸ºæ???
        	"desc": "?°ç???¿®å¤??xxx??????",     //?´æ????
        	"updateurl": "http://xxx"           // ?¨çº¿ä¸?½½?°å?ï¼??ç¬?¸²ï¼??????°æ?è¿??""
    	}
	}
		 */
		

		public UpdateDetail getUpdateVersion() throws NoConnectException, IOException, ParserException{
			UpdateDetail updateDetail = null;
			Bundle bundle = new Bundle() ;
			bundle.putString("type", UpdateDetail.Dtype) ;
//			bundle.putString("version",  UpdateDetail.Version) ;
			NetResult result =  this.doMeishidarenPost(APPUPDATE_BASIC_URL, bundle);
			if(result.getErrorCode().equals("0")){
				updateDetail  = JsonParser.parseUpdateResult(result.getRetContent()) ;
			}else{
				throw new ParserException(result.getErrorMessage() ) ;
			}
			return updateDetail;
		}
		
//3.20.	??´¢???
//Methodï¼?eleteTweet
//è°?????
//???	??è¿???å¼?
//uid	?¨æ????ID	å­??ä¸?
//password	å¯??	å­??ä¸?
//tweetid	ç¾??id
//
//è¿??é¡¹è????
//è¿??é¡???è¿???å¼?
//status	??????	0è¡¨ç¤º???ï¼??ä»?¸ºå¤±è´¥???ä»£ç?
//msg	æ¶??	??????ï¼??å¤±è´¥???
//content	è¿?????	???
public NetResult deleteTweet(String uid, String psw, String tweetid) throws NoConnectException, IOException, java.io.IOException, ParserException{
	   Bundle bundle = new Bundle();
	   bundle.putString("uid", uid);
	   bundle.putString("password",  psw);
	   bundle.putString("tweetid", tweetid);
	   NetResult result =  this.doMeishidarenPost(DELETETWEET, bundle);
	   if(!result.getErrorCode().equals("0")){
				throw new ParserException(result.getErrorMessage() ) ;
	   }
	return result; 
}

}
