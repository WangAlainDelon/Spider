package com.wx.spider1;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.codecraft.webmagic.Spider;

public class Spider1 {


    //public static  UserService userService;
	public static  void main(String args[]) {				
	        System.out.println("========知乎用户信息小爬虫【启动】喽！=========");
	        //ApplicationContext context =new ClassPathXmlApplicationContext("beans.xml");
	        //userService= (UserService)context.getBean("userService");
	        //userService.save();
	        Spider.create(new UserPageProcessor()).addUrl("https://www.zhihu.com/search?type=people&q=java").thread(5).run();	       
	}
}
