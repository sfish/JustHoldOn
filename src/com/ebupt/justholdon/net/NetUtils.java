package com.ebupt.justholdon.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.ebupt.boxlunch.datasource.LunchboxDatabase;
import com.ebupt.boxlunch.exception.NoConnectException;
import com.ebupt.boxlunch.util.ShortCut;

public class NetUtils {

	public static String TIME_OUT = "ç½??è¿?????ï¼??æ£???¨ç?ç½?????æ­£å¸¸";
	private static String TAG = "NetUtils";
 	private static Context context;
	private static int REQUEST_MAX_TIME = 50000;
	private String cachePath = "Boxlunch";
	private static File cache ;
	public static final int NO_NETWORK=0,EB_NET=1,OUT_NET=2,UNKNOWN=3;
	
	//private static String  cacheUrlStrings = "getRecomandListgetRecomandSubListgetsongTypeListgetsongSubTypeListgetSongSubListgetboxTypeListgetboxSubListgetMusicBoxgetHotWords";
	
	public static boolean hasConnect() {
		ConnectivityManager manager = (ConnectivityManager) NetUtils.context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = manager.getActiveNetworkInfo();
		// Log.i("con", "NoConnect") ;
		// Log.i("con", manager.toString()) ;
		// Log.i("con", ""+info.toString()) ;
		if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
			
			
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse;

			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_MAX_TIME);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					REQUEST_MAX_TIME);

			try {
				HttpGet httpRequest = new HttpGet(NetEngine.APPUPDATE_BASIC_URL);
				httpResponse = httpclient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String re = EntityUtils.toString(httpResponse.getEntity());
					Log.v("CHeck net", re) ;
					if(re.indexOf("error") > 0){	
						Log.v("CHeck net", "yes") ;				
						return true;					
					}
				}
			} catch (Exception e) {
				return false ;
			}
		}
		return false;
	}

	public static int checkisEBNet(Context mContext) {
		context = mContext;
		ConnectivityManager manager = (ConnectivityManager) NetUtils.context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = manager.getActiveNetworkInfo();

		if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse;

			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					REQUEST_MAX_TIME);

			try {
				HttpGet httpRequest = new HttpGet("http://sendpass.ldap.ebupt.com/");
				httpResponse = httpclient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String re = EntityUtils.toString(httpResponse.getEntity());
					Log.v("CHeck net1", re) ;
					return EB_NET;
				}
				else {
					return OUT_NET;
				}
//				HttpGet httpRequest2 = new HttpGet("http://wap.baidu.com/");
//				httpResponse = httpclient.execute(httpRequest2);
//				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					String re = EntityUtils.toString(httpResponse.getEntity());
//					Log.v("CHeck net2", re) ;
//					return OUT_NET;
//				}
			} catch (Exception e) {
				Log.d(TAG, "æ£??ç½???????????ï¼??");
				Log.d(TAG, "æ£??ç½?????"+e.toString());
				//?????è¶??
				// æ£??ç½?????org.apache.http.conn.ConnectTimeoutException: Connect to /10.1.32.10:80 timed out
				return UNKNOWN;//????´æ??¤å?ä¸ºå?ç½?
			}
		}
		return NO_NETWORK;
	}
	
	public static String openUrl(String url, String method, Bundle bundle,
			Context context) throws NoConnectException, IOException {
		NetUtils.context = context;
		Log.d(TAG,"url====="+url);

//		if (!NetUtils.hasConnect()) {
//			throw new NoConnectException("");
//		}
		String ret = "";//getResultCache(context, url, bundle);

//		if (ret!=null&&!ret.equals("")) 
//		{
//			Log.i(TAG, "?°æ?åº??å®¹å??¨ä????????´æ??????????");
//			return ret;
//		}
//		Log.i(TAG, "?°æ?åº??å®¹æ?å­?????å·²è??????µ·ç½??è®¿é?");
//		//TODO:æ·»å?https???
		HttpClient httpclient = null;
//		if (url.startsWith("https"))
//		{
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			//schemeRegistry.register(new Scheme("https", new EasySSLSocketFactory(), 8443)); 
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params,30 * 1000);
			HttpConnectionParams.setSoTimeout(params, 30 * 1000); 
			params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 30);
			params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(30));
			params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			ClientConnectionManager cm = new SingleClientConnManager(params, schemeRegistry);
			httpclient = new DefaultHttpClient(cm, params);
//		}
//		else {
//			httpclient =new DefaultHttpClient();
//		}
		//httpclient =new DefaultHttpClient();
		HttpResponse httpResponse;

		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_MAX_TIME);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				REQUEST_MAX_TIME);

//		if (url.contains("getUserTweets")) {
//			return getUserTweetsResult();
//		}
		
		if (method.equalsIgnoreCase("get")) {
			StringBuilder urlSb = new StringBuilder(url);
			urlSb.append(encodeUrl(bundle));
			Log.i(TAG, "get URL = "+urlSb.toString());
			HttpGet httpRequest = new HttpGet(urlSb.toString());
			Log.i(TAG, "??µ·ç½??è®¿é?");
			httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				ret = EntityUtils.toString(httpResponse.getEntity());
				//String urlshort= url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
//				if (cacheUrlStrings.contains(urlshort)) 
//				updateResultCache(context, url, bundle, ret);
				//Log.i(TAG, "ret = "+ret);
			}
			else {
				Log.i(TAG, "error code = "+httpResponse.getStatusLine().getStatusCode());
			}

		} else if (method.equalsIgnoreCase("post")) {
			HttpPost httpRequest = new HttpPost(url);
			Log.i(TAG, "post URL = "+url);
			HttpEntity httpentity;
			httpentity = new UrlEncodedFormEntity(formatPostData(bundle),
					"utf8");
			Log.v(TAG, httpentity.toString());

			httpRequest.setEntity(httpentity);
		
				Log.i(TAG, "??µ·POSTç½??è®¿é?");
				httpResponse = httpclient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					ret = EntityUtils.toString(httpResponse.getEntity());
				}
			
		}
		Log.i(TAG, "ret = "+ret);
		return ret;
	}
	
	/* 
     * å¯?sonå­??ä¸²è?è¡??æ»??²æ?ä¹±ç????æ¡?
     */  
    public static String JsonFilter(String jsonstr){  
        return jsonstr.substring(jsonstr.indexOf("{")).replace("\r\n","\n");   
    } 
	
	public static String uploadPic(String url, String method, Bundle bundle,
			Context context,InputStream is,String filename) throws NoConnectException, IOException {
		NetUtils.context = context;
		Log.d(TAG,"url====="+url);
		String ret = "";//getResultCache(context, url, bundle);

		HttpClient httpclient = null;
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			//schemeRegistry.register(new Scheme("https", new EasySSLSocketFactory(), 8443)); 
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params,30 * 1000);
			HttpConnectionParams.setSoTimeout(params, 30 * 1000); 
			params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 30);
			params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(30));
			params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			ClientConnectionManager cm = new SingleClientConnManager(params, schemeRegistry);
			httpclient = new DefaultHttpClient(cm, params);
		HttpResponse httpResponse;

		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_MAX_TIME);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				REQUEST_MAX_TIME);

		HttpPost httpRequest = new HttpPost(url);
		Log.i(TAG, "post URL = "+url);
		
		InputStreamBody isb = new InputStreamBody(is, filename);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("uid", new StringBody(bundle.get("uid").toString())); 
        multipartEntity.addPart("password", new StringBody(bundle.get("password").toString())); 
        multipartEntity.addPart("file", isb); 
        httpRequest.setEntity(multipartEntity);
	
			Log.i(TAG, "??µ·POSTç½??è®¿é?");
			httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				ret = EntityUtils.toString(httpResponse.getEntity());
			}
			
		Log.i(TAG, "ret = "+ret);
		return ret;
	}
	
	
	
	
	public static Bitmap downloadPic(String iconurl,int bitmapSize) throws NoConnectException, IOException {
		//String name =  path.substring(path.lastIndexOf("."));
//		int start = iconurl.lastIndexOf("/");
//      int end = iconurl.length();
		try{
			cache= ShortCut.getCacheFile(context);
			Log.i(TAG, "url = "+iconurl);
			if (iconurl==null||iconurl.equals("")||!iconurl.startsWith("http")) return null;
			
			final String iconname = getUrlFileName(iconurl);
			Log.i(TAG, "iconname = "+iconname);
			if (iconname==null||iconname.equals("")||iconname.equals("/")) return null;
			
			
			File file = new File(cache, iconname);
			// å¦???¾ç?å­?????ç¼?????ï¼??ä¸??????¨ä?è½?
			if (file.exists()&&file.length()>0) {
				Log.d(TAG,"????·å?");
				return getImageBitmap(file.getPath(),bitmapSize);//Uri.fromFile(path)è¿?¸ª?¹æ??½å??°æ?ä»¶ç?URI
			} else {
				Log.d(TAG,"ç½???·å?");
				// ä»??ç»???·å??¾ç?
				URL url = new URL(iconurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(50000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				int correntLength = conn.getContentLength(); 
				Log.i(TAG, "?¾ç?å¤§å? = "+correntLength);
				
				if (conn.getResponseCode() == 200) {
	//				Log.i(TAG, "è¿??200.");
					InputStream is = conn.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
	//					Log.i(TAG, "å·²ä?è½??¾ç?å¤§å???? "+len);
						fos.write(buffer, 0, len);
					}
					is.close();
					fos.close();
					// è¿??ä¸?¸ªBitmapå¯¹è±¡
					if (correntLength>0&&correntLength== file.length())
					{
						return getImageBitmap(file.getPath(),1);
					}
					else return null;
				}
				else  //è¿??å¤±è´¥
					return null;
			}//end of else
		}catch(OutOfMemoryError e){
				//??????èµ?? 
				throw new OutOfMemoryError();
	//			return getImageBitmap(path,getImageBitmap*2);
	    }
	}
	
	/**
	   * ??d?¡å???½¬??itmap
	   * @param path sd card path
	   * @return bitmap
	   */
	  public final static Bitmap getImageBitmap(String path,int getImageBitmap)
	  { 
//		  BitmapFactory.Options options = new BitmapFactory.Options();
//	      options.inSampleSize = 1;
//	      Bitmap bm = BitmapFactory.decodeFile(path, options);
//	      return bm;
	      try{
		      BitmapFactory.Options opt = new BitmapFactory.Options();
		      opt.inPreferredConfig = Bitmap.Config.RGB_565;
		      opt.inPurgeable = true;
		      opt.inInputShareable = true;
		      opt.inSampleSize=getImageBitmap;
		      //?·å?èµ???¾ç?
		      return BitmapFactory.decodeFile(path,opt);
	      }catch(OutOfMemoryError e){
				Log.d("my","out!!!!!"+"getImageBitmap=="+path);
				//??????èµ?? 
				throw new OutOfMemoryError();
//				return getImageBitmap(path,getImageBitmap*2);
				//TODO
	      }
	      
	  }
	  
	  public static String getUrlFileName(String iconurl)
		{
			int start = iconurl.lastIndexOf("/");
	      int end = iconurl.length();
	      String iconname = iconurl.substring(start, end);
	      return iconname; //å¹¿å??¾ç?
		}


	public static String encodeUrl(Bundle bundle)
			throws UnsupportedEncodingException {
		if (bundle.isEmpty()) {
			return "";
		}
		StringBuilder params = new StringBuilder();
		Set<String> keySet = bundle.keySet();
		Iterator<String> it = keySet.iterator();
		StringBuilder key = null;
		while (it.hasNext()) {
			key = new StringBuilder(it.next());
			params.append('&');
			
			params.append(URLEncoder.encode(key.toString(), "UTF-8"));
			params.append('=');
			params.append(URLEncoder.encode(bundle.getString(key.toString()),
					"UTF-8"));
		}

		return params.toString().replaceFirst("&", "?");
	}

	public static List<NameValuePair> formatPostData(Bundle bundle)
			throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<String> keySet = bundle.keySet();
		Iterator<String> it = keySet.iterator();
		StringBuilder key = null;
		while (it.hasNext()) {
			key = new StringBuilder(it.next());
			Object object = bundle.get(key.toString());
			if (object != null) {
				String value = null;
				if (object.getClass().equals(String.class)) {
					value = bundle.getString(key.toString());
				}
				Log.i(TAG, "post para:"+key.toString() + "|" + value);
				params.add(new BasicNameValuePair(key.toString(), value));
			}
		}
		// Log.v("End___middle:", params.toString());
		return params;
	}

	//æµ???°æ?ï¼?
	private static String getQuickRegisterResult()
	{
		String ret = "";
		ret="{\"status\": \"0\",\"msg\": \"\",\"content\":{\"uid\": 3768,\"username\": \"Ut1D6X\",\"password\": \"bwgrWjOz\"}}";		
		return ret;
	}
	
	private static String getUserTweetsResult()
	{
		String ret = "";
		ret="{\"status\":\"0\",\"msg\": \"???\",\"content\":[{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}," +
				"{\"uid\":\"2778\",\"username\":\"å¿?? ??",\"userpic\":\"http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg\",\"itemid\":\"001\",\"text\":\"é»?·¨????¥ä¸»???ä¸??å·¨æ????ï¼??ï¼?",\"pic\":\"https://www.google.com/images/srpr/logo3w.png\",\"commentnum\":\"9826\",\"likenum\":\"17486\",\"date\":\"20120711121130\"}]}";		
		return ret;
	}
	

}
