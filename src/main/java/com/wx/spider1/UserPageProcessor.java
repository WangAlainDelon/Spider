package com.wx.spider1;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
public class UserPageProcessor implements PageProcessor {

	private final static Site site=Site.me().setRetryTimes(3).setSleepTime(1000);
	private static UserService userService;
	static{
		 ApplicationContext context =new ClassPathXmlApplicationContext("beans.xml");
		 userService= (UserService)context.getBean("userService");	     
	}
	
	//每一个请求链接的页面都会来这里进行页面解析
	public void process(Page page) {
		//如果是标题集合页面就将链接加入到请求的队列中来，page.getUrl()是拿到当前请求的链接
		Selectable url = page.getUrl();
		//System.out.println(urls); 
		//输出：https://www.zhihu.com/search?type=people&q=java get page: https://www.zhihu.com/search?type=people&q=java				
		//如果确实是访问列表的页面，就把列表页面的具体链接提取出来
		if(url.regex("https://www\\.zhihu\\.com/search\\?type=people&q=java").match())
		{
			Selectable xpath = page.getHtml().xpath("//div[@class='List-item']//h2[@class='ContentItem-title']//a/@href");
			List<String> all = xpath.all();
			for (String string : all) {
				System.out.println("要提取页面的链接："+string);
			}
			//添加到待爬取队列
			page.addTargetRequests(all);
			
		}
		//否则就提取页面
		else if(url.regex("https://www\\.zhihu\\.com/[\\w\\W]*").match())
		{
			User user=new User();
			String name=page.getHtml().xpath("//span[@class='ProfileHeader-name']/text()").get();
			System.out.println("name为："+name);
			if(name!=null)
			{
				String introduce=page.getHtml().xpath("//*[@id='ProfileHeader']/div/div[2]/div/div[2]/div[1]/h1/span[2]/text()").get();
				String ask=page.getHtml().xpath("//*[@id='ProfileMain']/div[1]/ul/li[2]/a/span/text()").get();
				String answer=page.getHtml().xpath("//*[@id='ProfileMain']/div[1]/ul/li[3]/a/span/text()").get();
				System.out.println("answer数为："+answer);
				user.setName(name);
				user.setIntroduce(introduce);
				user.setAsk("回答："+ask);
				user.setAnswer("提问："+answer);
			}
			userService.save(user);									
		}
		
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	

}
