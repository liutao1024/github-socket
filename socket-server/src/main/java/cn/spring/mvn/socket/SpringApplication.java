package cn.spring.mvn.socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.spring.mvn.socket.entity.User;
import cn.spring.mvn.socket.entity.service.UserService;
//import cn.spring.mvn.socket.server.SocketManager;

/**
 * @author LiuTao @date 2018年9月3日 下午1:52:50
 * @ClassName: SpringApplication 
 * @Description: TODO(非web项目spring的启动类)
 */
public class SpringApplication {
	private static String config_1 = "spring.xml";
	private static String config_2 = "mybatis.xml";
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config_1,config_2);
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
	/**
	 * @author LiuTao @date 2018年9月3日 下午1:52:13 
	 * @Title: main 
	 * @Description: TODO(启动项目的main方法) 
	 * @param args
	 */
	public static void main(String[] args) {
//		UserService userServiceImpl = getApplicationContext().getBean("UserService",UserService.class);
		UserService userServiceImpl = applicationContext.getBean("UserService",UserService.class);
		User user = userServiceImpl.getUser("1001");
		System.out.println(user);
//		SocketManager.initializeServer();
		System.out.println("12132131231231");
	}
}
