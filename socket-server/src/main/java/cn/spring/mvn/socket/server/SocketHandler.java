package cn.spring.mvn.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;

import cn.spring.mvn.socket.base.SpringTool;
import cn.spring.mvn.socket.entity.service.UserService;
import cn.spring.mvn.socket.entity.service.impl.UserServiceImpl;
import cn.spring.mvn.socket.test.UserServiceImplTest;

public class SocketHandler implements Runnable {
	//Socket tools
	private Socket socket;
	//charSet - UTF-8
	private String charSetStr = "UTF-8";
	//Constructor Method
	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Autowired
	private UserService userServiceImpl = (UserService) SpringTool.getBean("UserService");
	
	@Override
	public void run() {
		BufferedWriter bufferedWriter = null;
		String requestStr = ""; 
		String responseStr = "";
		try {
			/**
			 * 步骤: 
			 * 1.通过Socket创建输入流 ,将Socket的输入流转换成Json报文
			 * 2.读取客户端发送过来的json格式的请求报文
			 * 3.对请求报文进行解析处理后,将处理结果通过Socket创建输出流 
			 * 4.回复客户端"OK"
			 */
			System.out.println("[INFO]========客户端地址: " + socket.getInetAddress().getHostAddress());
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), charSetStr);//解决中文字符乱码问题
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			requestStr = bufferedReader.readLine();
            System.out.println("[INFO]========请求json报文: " + requestStr);
//          responseStr = SocketHandlerImpl.callInterface(requestStr);//responseMap.toString();
//            responseStr = requestStr;//测试直接将请求返回
//            responseStr = UserServiceImplTest.getString("1001");
            responseStr = userServiceImpl.getUser("1001").toString();
            System.out.println("[INFO]========响应json报文: " + responseStr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), charSetStr);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(responseStr);
		} catch (Exception e) {
			try {//some Exception in try return some message 
				responseStr =  "{" + 
									"\"comm\":{\"corecd\":\"\",\"mesage\":\"" + e.getMessage() + "\",\"asktyp\":\"\",\"status\":\"ERROR\"}," + 
									"\"sys\":{\"servtp\":\"\",\"servno\":\"\",\"serial\":\"" + "这儿需要一个序列号" + "\",\"corpno\":\"\"}" +
							   "}";
				bufferedWriter.write(responseStr);
			} catch (IOException IOe) {
				System.out.println("[ERROR]========服务器 run()异常响应组装报文异常:" + IOe.getMessage()); 
				return;
			} 
			System.out.println("[ERROR]========服务器 run()异常响应:" + e.getMessage()); 
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.flush();
				} catch (IOException e) {
					System.out.println("[ERROR]========服务器bufferedWriter.flush()异常:" + e.getMessage());
					return;
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("[ERROR]========服务器socket.close()异常:" + e.getMessage());
					return;
				}
			}
			System.out.println("[INFO]========Socket服务器响应结束!"); 
		}
	}

}
