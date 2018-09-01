package cn.spring.mvn.socket.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SocketBase {
	private static ApplicationContext applicationContext = null;
	/**
	 * @author LiuTao @date 2018年8月17日 上午9:54:54 
	 * @Title: getApplicationContext 
	 * @Description: 通过配置文件名获取APP的上下文中 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		String config_1 = "spring.xml";
		String config_2 = "mybatis.xml";
		applicationContext = new ClassPathXmlApplicationContext(config_1,config_2);
		return applicationContext;
	}


}
