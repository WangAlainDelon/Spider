package com.wx.spider1;

public class User {

	//用户的名字
	private String name;
	//用户的简介
	private String introduce;
	//用户提问
	private String ask;
	//用户回答
	private String answer;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
	@Override
	public String toString() {
		return name+introduce+ask+answer;
	}
}
