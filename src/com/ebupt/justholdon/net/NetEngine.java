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
	

	//  ?��?URL
	private final String Meishidaren_BASIC_URL = BSAE_URL+ "/api/" ;

	//private final String REGISTER_PAGE = "reg.do" ;
	

	//注�??��? ?��?URL
	private final String REGISTER_BASIC_URL = BSAE_URL+ "/api/" ;
	private final String REGISTER_PAGE = "register" ;
	
	
	
	public static final String APPUPDATE_BASIC_URL = "version" ;
	private final String NO_ANSWER = "对�?起�????�???��??��?�??";
		
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
	

	
	
//  /*4.2  解�?�??- ???8.2
//	http://203.181.161.230:8080/chailing/api/unbindweibo.do? 
//	???说�?:
//   	  msisdn : ?��?????��?
//	  psw : ?��?�??----�??urlencode, utf8
//	�??�??:
//	  {
//		"errorCode":0,    --???�??0
//		"errorMessage":"", --errorCode �?��0??�???��????信�?
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
	
	
// /* 4.3  �?????�??- ???4.4.	?��??��?信�??��?
//	http://203.181.161.230:8080/chailing/api/querybindweibo.do? 
//	???说�?:
//   	  msisdn : ?��?????��?
//	  psw : ?��?�??----�??urlencode, utf8
//	�??�??:
//	  {
//		"errorCode":0,    --???�??0
//		"errorMessage":"", --errorCode �?��0??�???��????信�?
//		"weiboFlag":0 ---- 0:已�?�??  1:�??�??
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
	1.1	�??注�?
	method:register

	�?????
 	??

	�??项�????
	�??�???�???�?
	status	??????	0表示???�??�?��失败???代�?
	msg	�??	??????�??失败???
	content	�?????	JSONObject
	content.uid	?��???�??�?
	content.password	�??	�??�?
		

	示�?
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
	 *  1.2	?��?
	method�?ogin

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
type	?��?类�?	0�?????�?
1�??�?��??��??
2�?��人账??
key	?��?�??	�??�?

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.uid	?��???�??�?
content.password	�??	�??�?
content.*	?��?信�?�??�??称�?	�??�?
说�?�?��?��?�?��???社交�??�???��??��?uid�?assword为空�??�?ey�?????�?id??assword ??��示�?该社交�?�?��?��?�???��????�??????��????�?????�??�??�??使�?社交�??�???��??��??��?使�?�??对�??��???????

示�?
192.168.0.137/api/login
?��?:POST
??? uid=3765 password=oe4hqqRx type=0
{
    "status": "0",
    "msg": "",
    "content": {
        "uid": "3765",
        "avatar":"/pic/user/default.jpg",
        "sex": "???�?,
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
	 * 1.3	�??社交�??�??
  method�?ind
�?????�??�?��交�?�?��?��??��?�????
�?????
???	??�???�?
uid	?��????ID	�??�?
type	?��?类�?	1�??�?��??��??
				2�?��人账??
				3:?��?�??
key	?��?�??	�??�?

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	???

	  
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
	 *1.4	�???��?信�?
	 *method: modifyUserInfo
	 *�?????
	 *???	??�???�?
	 *uid=value	?��????ID	�??�?
	 *password=value	�??	�??�?
	 **=value	�?��?��????	�??�?
	 *�??项�????
	 *�??�???�???�?
	 *status	??????	0表示???�??�?��失败???代�?
	 *msg	�??	??????�??失败???
	 *content	�?????	???
	 *说�?�???��??��???id ??assword ?????���??�????????
	 *?��?????��?表示�?��?��??????
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
	 *   1.5  �??�??
	 *   method:resetPassword
		???说�?:
	   	  uid : ?��?????��?
		  password : ?��???---�??urlencode, utf8
		  newpassword : ?��???---�??urlencode, utf8
		�??�??:
		  {
			"status":0,    --???�??0
			"msg":"", --errorCode �?��0??�???��????信�?
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
		 *   1.6	�???��???
		 *   method:resetPassword
			???说�?:
		   	  uid : ?��????ID
			  password : �??----�??urlencode, utf8
			  username : ?��??��???-
			�??�??:
			  {
				"status":0,    --???�??0
				"msg":"", --errorCode�?��0??�???��????信�?
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
		 *   2.1	�???��?
Method�?ploadPic

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
 InputStream	
 filename	 ?��????

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.picid	?��???d	�??�?
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
		 *   2.2	???�??�??
		 *   Method�?oTweet

	�?????
	???	??�???�?
picid	?��?id	�??串�?�???��??????
uid	?��????ID	�??�?
password	�??	�??�?
text	??????	�??�?
type	类�?	�??串�?广�???

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	???

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
		 *   2.3	????��?
Method�?ddFavorite

�?????
???	??�???�?
itemid	�??itemid	�??�?
uid	?��????ID	�??�?
password	�??	�??�?

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	???


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
		 *   2.4	�???��???��
Method�?etFavorites

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.list	?��???��	JSONArray
content.list.text	�??Item???�??�?�??�?
content.list.pic	�??Item对�?????????url

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
				 *   3.1.	???�?
				 *   Method�?ddFocus
					???说�?:
				   	  uid : ?��????ID
					  password : ?��???---�??urlencode, utf8
					  focusuid : �??注�??��?ID
					�??�??:
					  {
						"status":0,    --???�??0
						"msg":"", --errorCode �?��0??�???��????信�?
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
					 *   3.2.	�???��?信�?
Method�?etUserInfo

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
userid	?��??????D	�??�?

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.username	?�称	�??�?
content.gender	?��?	�??�?
content.intro	???�??	�??�?
content.focusnum	?�注??�??�?
content.followernum	�????�??�?
content.tweetnum	�??�????�??�?
content.*	?��?	�??�?



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
					 *   3.3.	�???��?�??�??
Method�?etUserTweets

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
userid	?�注?��????�??	�??串�?�????�?��??????己�?注�??��????�??�???��?id表�?�??�?d????????
num	页�?	?��? �??20
tweetid	�??�??id	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
	updateway='refresh'�??�??请�????�?��id
updateway	?��??��?	'more' ??'refresh'

�??项�????
		
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	 JSONArray
content.usename	�??item???????��?	
content.uid	�??item?????d	�??�?
content.userpic	�??item?????��?????url
content.tweetid	�??id	�??�?
content.description	�??Item???�??�?�??�?
content.picurl	�??Item对�?????????url
content.commentnum	�??Item对�????论�?	�??�?
content.likenum	�??Item???�??�??	�??�?
content.dateline	�??Item????��?	20120330172800



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
	 * 4.2.	�?????�??�??
Method�?etTopTweets

�?????
???	??�???�?
uid	??��???�??	�??�?
password	�??	�??�?
num	?��?	?��?�??�?0

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.usename	�??item???????��?	
content.uid	�??item?????d	�??�?
content.userpic	�??item?????��?????url
content.tweetid	�??id	�??�?
content.type	类�?	�??串�?便�?类�? 0 1 2 3 
content.ingredient	主�?	�??�?
content.staple
主�?	�??�?
content.accessories	�??	�??�?
content.location	�?��	�??�?
content.latitude	�?��	�??�?
content .longitude	�?��	�??�?
content.foodname	???	�??�?
content.description	�??Item???�??�?�??�?
content.picurl	�??Item对�?????????url
content.commentnum	�??Item对�????论�?	�??�?
content.goodnum	�??Item???�??�??	�??�?
content.dateline	�??Item????��?	20120330172800
	
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
		 * 3.18.	?????????�??
Method�?etMyShares

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
updateway	?��??��?	�??串�?'more' ??'refresh'
num	?��??��?	�??�??20??
id	�???��?????????d	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
updateway='refresh'�??�??请�????�?��id

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONArray
content.id	id	�??�?
content.dateline	�???????�??�?
content.commentcontent	�?????�?�??串�???????论�??��????为空
Content.picurl	?��?�????????�????rl	????????????�???��????为空
Content.foodname	�?????�?
content. sharetonames	�??????��?	�??串�??��??��???
content.tweetid	�??�????d	�??�?
content.description	?��?�?????�?�??串�?

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
		
//		3.4.	?��?�??id�???��?�??�??
//		Method�?howOneTweet
//
//		�?????
//		???	??�???�?
//		uid	?��????ID	�??�?
//		password	�??	�??�?
//		tweetid	�??�??id	
//				
//
//		�??项�????
//				
//		status	??????	0表示???�??�?��失败???代�?
//		msg	�??	??????�??失败???
//		content	�?????	 JSONArray
//		content.usename	�??item???????��?	
//		content.uid	�??item?????d	�??�?
//		content.userpic	�??item?????��?????url
//		content.tweetid	�??id	�??�?
//		content.type	类�?	�??串�?便�?类�? 0 1 2 3 
//		content.ingredient	主�?	�??�?
//		content.staple
//		主�?	�??�?
//		content.accessories	�??	�??�?
//		content.foodname	???	�??�?
//		content.description	�??Item???�??�?�??�?
//		content.picurl	�??Item对�?????????url
//		content.commentnum	�??Item对�????论�?	�??�?
//		content.likenum	�??Item???�??�??	�??�?
//		content.dateline	�??Item????��?	20120330172800
//		content.islike	�???��????�??	0??1 ??

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
	 * 3.4.	???�????��
	 * Method�?etUserFollowers
	 * 
	 * �?????
		???	??�???�?
		uid	?��????ID	�??�?
		password	�??	�??�?
		who	???�??表�???d	
		num	�??????��?	�??20
		id	�??�????��?????????�?d	�??次�???

		�??项�????
		�??�???�???�?
		status	??????	0表示???�??�?��失败???代�?
		msg	�??	??????�??失败???
		content	�?????	 JSONArray
		content.uid	�????d	�??�?
		content. username	�??????��?	�??�?
		content. avatar	�????��??url	
		content. tweet	�?????�???�微???�?�??�?

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
	 * 3.4.	�??????��?信�?
Method�?etTopUsers

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
num	?��?	

�??项�????
  
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.username	?�称	�??�?
Content.isvip	Vip?��?	0 �?? 1 ??
content.isresetpassword	?????���??	0 代表没�?�?代表??���?
content.email	???	�??�?
content.sex	?��?	�??�?
content.introduce	???�??	�??�?
content.birthday	???	�??�?
content.constellation	??��	�??�?
content.area	?��?	�??�?
content.sina	?�浪�??主页?��?	�??�?
content.renren	人人主页?��?	�??�?
content.weixin	�?���??	�??�?
content.cellphone	????��?	�??�?
content.focusnum	?�注??�??�?
content.fansrnum	�????�??�?
content.tweetnum	�??�????�??�?
content.uid	?��?uid	�??�?
Content.avatar	?��?头�?	url
content.weiboshare	�????��	0代表???�????���?代表�??�??�??2代表�??�??�??�?
Content.isfocus	??????注�?	0代表???�??1代表?�注,2代表???己�?�??该�?示�????�?

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
	 * 3.5.	????�注??��
	 * Method�?etUserFocus
	 * 
	 * �?????
???	??�???�?
uid	?��?id	�??�?
password	�??	�??�?
who	???注�?表�???d	
num	�??????��?	�??20
id	�???�注??��?????????�?d	�??次�??��?0

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONArray
content. uid	�????d	�??�?
content. avatar	�????��??url
content. username	�??????��?	�??�?
content.dateline	?�注?��?	
content.id	?�注id	
content. tweet	�?????�???�微???�?�??�?


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
	
	
//	1.5	?��?社交好�?
//	method�?ocialFriends
//
//	�?????
//	???	??�???�?
//	username	?��???�??�?
//	password	�??	�??�?
//
//	�??项�????
//	�??�???�???�?
//	status	??????	0表示???�??�?��失败???代�?
//	msg	�??	??????�??失败???
//	content	�?????	JSONObject
//	content.uid	社交�??id	�??�?
//	content.username	社交�???��???�??�?
//	Content.avatar	社交�???????�?���??�????rl�?????�????��交�?�????
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
	 *   3.6.	�??
	 *   Method�?oComment
	 *    
		???说�?:
	   	  uid : ?��????ID
	   	  password	�??	�??�?
		  comment	�?????	�??�?
		  tweetid	�?????�?��??d	�??�?
		�??�??:
		  {
			"status":0,    --???�??0
			"msg":"", --errorCode �?��0??�???��????信�?
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
		 * 3.7.	?��????�??�??�????��
Method�?etComments

�?????
???	??�???�?
uid	??��??d	�??�?
password	�??	�??�?
tweetid	�??�??id	�??�?
num	?��??��?	�??�??20??
commentid	�???��????????��?�?�??次�?�??认为0

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONArray
Content.username	�???��???
content. uid	�???��?id	�??�?
content. userpic	�????��?????url
content. commentid	�????d	�??�?
content. comment	�?????�?�??�?
Content.dateline	�???��?	



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
		 *   3.8.	�?
		 *   Method�?oGood
		 *    
			???说�?:
		   	  uid : ?��????ID
		   	  password	�??	�??�?
			  tweetid	�?????�?��??d	�??�?
			�??�??:
			  {
				"status":0,    --???�??0
				"msg":"", --errorCode �?��0??�???��????信�?
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
			
			/*	3.9.	???�????��
			 *�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
tweetid	�??�??id	�??�?
num	?��??��?	�??�??20??
goodtid	�???��????????��?	�??次�?�??认为0

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONArray
content.goodid	�??id	�??�?
content. uid	?????�??�?
Content.username	�???��???
content. userpic	�??头�??��?	url
Content.dateline	�???��?	


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
			 *   3.10.	???�?????�?
Method�?etMessages

�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
updateway	?��??��?	�??串�?'more' ??'refresh'
num	?��??��?	�??�??20??
msgid	�???��????????��?	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
updateway='refresh'�??�??请�????�?��id

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONArray
content.msgid	�????d	�??�?
content.fromuid	�???????�??�?
content.fromusername	???????��???�??�?
content.fromuserpic	??????头�??��?	url
content.dateline	�???????�??�?
content.commentcontent	�?????�?�??串�?�???��?为空
content.msgtype	�?????�?�??串�?0为�?论�?1为�?
content.tweetid	�??�????d	�??�?
content.tweetdescription	�??�???????��??�??�?


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
			 *   4.1.	�????���??�??
Method�?etCategoryTweets

�?????
???	??�???�?
uid	??��???�??	�??�?
password	�??	�??�?
type	??��	�??串�?�??�??类�?
num	?��?	?��?�??�?0
tweetid	�??�??id	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
					updateway='refresh'�??�??请�????�?��id
updateway	?��??��?	'more' ??'refresh' 

�??项�????
�??�???�???�?
status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
content	�?????	JSONObject
content.usename	�??item???????��?	
content.uid	�??item?????d	�??�?
content.userpic	�??item?????��?????url
content.tweetid	�??id	�??�?
content.description	�??Item???�??�?�??�?
content.picurl	�??Item对�?????????url
content.commentnum	�??Item对�????论�?	�??�?
content.likenum	�??Item???�??�??	�??�?
content.dateline	�??Item????��?	20120330172800

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
 *  3.19.	??��
Method�?oSearch


�?????
???	??�???�?
uid	?��????ID	�??�?
password	�??	�??�?
keyword	??��?��?�?�??�?
pagenum	�??�?�?�??

�??项�????

status	??????	0表示???�??�?��失败???代�?
msg	�??	??????�??失败???
totalpagenum	?�页???��?
totalnum	�???��?	?��?
content	�?????	 JSONArray
content.usename	�??item???????��?	
content.uid	�??item?????d	�??�?
content.userpic	�??item?????��?????url
content.tweetid	�??id	�??�?
content.description	�??Item???�??�?�??�?
content.picurl	�??Item对�?????????url
content.commentnum	�??Item对�????论�?	�??�?
content.likenum	�??Item???�??�??	�??�?
content.dateline	�??Item????��?	20120330172800 
content.islike	�???��????�??	0??1 ??
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
	System.out.println("?��?�??++++++++++++++++="+keyword);
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
	System.out.println("?��?�??++++++++++++++++="+keyword);
	if(result.get("status").equals("0")){
		  users  = JsonParser.parseSearchUsers(result.get("content")) ;
	}else{
		throw new ParserException(result.get("msg") ) ;
	}
	return users;
}

//3.11.	?????���??�????��
//Method�?etUserGoodSweets
//
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//userid	?��?id	�????????己�????�??�?��??��为空
//num	?��??��?	�??�??20??
//		
//tweetid	�??�??id	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
//updateway='refresh'�??�??请�????�?��id
//		
//updateway	?��??��?	'more' ??'refresh'
//		
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	JSONArray
//content.usename	�??item???????��?	
//content.uid	�??item?????d	�??�?
//content.userpic	�??item?????��?????url
//content.tweetid	�??id	�??�?
//content.type	类�?	�??串�?便�?类�? 0 1 2 3 
//content.ingredient	主�?	�??�?
//content.staple
//主�?	�??�?
//content.accessories	�??	�??�?
//content.foodname	???	�??�?
//content.description	�??Item???�??�?�??�?
//content.picurl	�??Item对�?????????url
//content.commentnum	�??Item对�????论�?	�??�?
//content.likenum	�??Item???�??�??	�??�?
//content.dateline	�??Item????��?	20120330172800
//content.islike	�???��????�??	0??1 ??

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

//3.12.	???�?????�?
//Method�?etMessages
//
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//updateway	?��??��?	�??串�?'more' ??'refresh'
//num	?��??��?	�??�??20??
//msgid	�???��????????��?	updateway='more'�?????次�?�?????�?���??id(�??次�?�?�?
//updateway='refresh'�??�??请�????�?��id
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	JSONArray
//content.msgid	�????d	�??�?
//content.fromuid	�???????�??�?
//content.fromusername	???????��???�??�?
//content.fromuserpic	??????头�??��?	url
//content.dateline	�???????�??�?
//content.commentcontent	�?????�?�??串�???????论�??��????为空�???��?为空
//Content.tweetpicurl	?��?�????????�????rl	????????????�???��????为空
//Content.foodname	�?????�?
//content.msgtype	�?????�?�??串�?0为�?论�?1为�?�?为�?�?3为�???
//content.tweetid	�??�????d	�??�?
//content.sharetweetdescription	?��?�?????述�?�?��??????信�?	�??串�?????????????为空
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

//3.16.	?��?�????��
//Method�?eadMessages
//
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//msgid	??��msgid	
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	JSONArray
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


//3.13.	?��???���??�??
//Method�?hareTweet
//
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//descriptioncomment	?��???���?????	�??�?
//tweetid	�?????�?��??d	�??�?
//Atuserids	�?人�??�好???uid	�??串�?�?��?��??????
//weibouserids	�???��??��???uid	�??串�?�?��?��??????
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	???
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

//3.20.	??��???
//Method�?etTopSearchWords
//
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//num	?��?  ??????????��?????��?�????��?��??????????�?*num,num�??�?0
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	???
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
		 * 5.1.	�???��??��? 
		 * Method�?ppupdate
		 *  
	???:
	  type: 设�?类�?�??须�??????????�??类�???iphone,android]
	  version: 设�?app�???????
	  �??�??
	{
    	"status": "0",
    	"msg": "???",
    	"content": {
        	"update": 1,                         // ????��?�???��?0为�??��?�?为�???
        	"desc": "?��???���??xxx??????",     //?��????
        	"updateurl": "http://xxx"           // ?�线�?��?��?�??�?���??????��?�??""
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
		
//3.20.	??��???
//Method�?eleteTweet
//�?????
//???	??�???�?
//uid	?��????ID	�??�?
//password	�??	�??�?
//tweetid	�??id
//
//�??项�????
//�??�???�???�?
//status	??????	0表示???�??�?��失败???代�?
//msg	�??	??????�??失败???
//content	�?????	???
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
