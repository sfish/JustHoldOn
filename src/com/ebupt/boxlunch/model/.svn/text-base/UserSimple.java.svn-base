package com.ebupt.boxlunch.model;

import java.util.HashMap;
import java.util.Map;

import com.ebupt.boxlunch.datasource.LunchboxDatabase;

public class UserSimple {
	//private String pNum = null;
	
	private int	 state = -1;
	private String secCode = null;
	private boolean savePassword = false;
	private String recorded = "0" ;
	private String uid =null;//用户的唯一标识符
	private String username =null;//用户名，相当于昵称
	private String psw = null;// 用户密码
	private String gender = null;// 性别
	private String intro = null;// 自我介绍
	private String focusnum = null;// 关注数
	private String followernum = null;// 粉丝数
	private String tweetnum = null;// 美食微博数目
	private String recentTweet = null;// 美食微博数目
	private String userpic  = null;//头像地址
	private String picurl = null;
	private String tweetid = null;
	private String picurl1 = null;
	private String tweetid1 = null;
	
	
	
	public UserSimple(){
		init(null,null,null);
	}
	
	public UserSimple(String pNum, String psw){
		init(pNum,psw, recorded);
	}
	public UserSimple(String pNum, String psw, String recorded){
		init(pNum,psw, recorded);
	}
	private void init(String pNum, String psw, String recorded){
		this.uid = pNum;
		this.psw = psw;
		this.recorded = recorded ;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public boolean isRecorded() {
		if(this.recorded.equals(LunchboxDatabase.REMEMBER_PWD_NO)){
			return false;
		}
		return true;
	}

	public void setRecorded(String secCode) {
		this.recorded = secCode;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String pNum) {
		this.uid = pNum;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public boolean isSavePassword()
	{
		return savePassword ;
	}
	public void setSavePassword(boolean savePassword)
	{
		this.savePassword = savePassword;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Map<String, String> toMap(){
		Map<String, String>  ma = new HashMap<String, String>() ;
		ma.put("uid", this.uid);
		ma.put("pwd",  this.psw) ;
		ma.put("remember", this.recorded) ;
		return ma ;
	}

	public String getFollowernum() {
		return followernum;
	}

	public void setFollowernum(String followernum) {
		this.followernum = followernum;
	}

	public String getTweetnum() {
		return tweetnum;
	}

	public void setTweetnum(String tweetnum) {
		this.tweetnum = tweetnum;
	}

	public String getFocusnum() {
		return focusnum;
	}

	public void setFocusnum(String focusnum) {
		this.focusnum = focusnum;
	}

	public String getRecentTweet() {
		return recentTweet;
	}

	public void setRecentTweet(String recentTweet) {
		this.recentTweet = recentTweet;
	}

	public String getUserpic() {
		return userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getTweetid() {
		return tweetid;
	}

	public void setTweetid(String tweetid) {
		this.tweetid = tweetid;
	}

	public String getPicurl1() {
		return picurl1;
	}

	public void setPicurl1(String picurl1) {
		this.picurl1 = picurl1;
	}

	public String getTweetid1() {
		return tweetid1;
	}

	public void setTweetid1(String tweetid1) {
		this.tweetid1 = tweetid1;
	}
}
