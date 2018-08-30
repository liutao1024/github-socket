package cn.spring.mvc.server.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author LiuTao @date 2018年6月12日 下午1:54:22
 * @ClassName: Client
 * @Description: 向外部发送报文并获得返回结果报文
 */
public class SocketClient {
	/**
	 * @author LiuTao @date 2018年6月13日 上午9:20:39 
	 * @Title: callClientReturnString 
	 * @Description: TODO(Describe) 
	 * @param host
	 * @param port
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String callClientReturnString(String host, int port, Map<String, Object> requestMap) throws Exception{
		Socket socket = new Socket(host, port);
		//将requestMap转化为Json类型    
		JSONObject requestJsonObject = new JSONObject(requestMap);
		String requestStr = requestJsonObject.toString();
		byte[] requestByte = requestStr.getBytes();
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		dataOutputStream.write(requestByte);
		dataOutputStream.flush();
		socket.shutdownOutput();
		//读取服务器端数据    
		DataInputStream datInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
		String responseStr = datInputStream.readUTF(); 
		socket.close();
		System.out.println("输入的信息为："+requestStr); 
		System.out.println("输出的信息为："+responseStr); 
		return responseStr;
	}
	/**
	 * @author LiuTao @date 2018年6月13日 上午9:20:45 
	 * @Title: callClientReturnMap 
	 * @Description: TODO(Describe) 
	 * @param host
	 * @param port
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> callClientReturnMap(String host, int port, Map<String, Object> requestMap) throws Exception{
        JSONObject responseJsonObject = callClientReturnJson(host, port, requestMap);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.putAll(responseJsonObject);
        //将Map数据返回
        return responseMap;
	}
	/**
	 * @author LiuTao @date 2018年6月13日 上午9:20:49 
	 * @Title: callClientReturnJson 
	 * @Description: TODO(Describe) 
	 * @param host
	 * @param port
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject callClientReturnJson(String host, int port, Map<String, Object> requestMap) throws Exception{
		String responseStr = callClientReturnString(host, port, requestMap);
		JSONObject responseJsonObject = JSONObject.parseObject(responseStr);  
		return responseJsonObject;
	}
	
	
	
	/**
	 * @author LiuTao @date 2018年6月13日 上午9:34:16 
	 * @Title: main 
	 * @Description: TODO(Describe) 
	 * @param args
	 */
	public static void main(String args[]) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		
		Map<String, Object> sysMap = new HashMap<String, Object>();
		Map<String, Object> commMap = new HashMap<String, Object>();
		Map<String, Object> srcMap = new HashMap<String, Object>();
		sysMap.put("corpno", "001");
		sysMap.put("servtp", "01");
		sysMap.put("servno", "02");
		
		commMap.put("corecd", "opcust");
		commMap.put("asktyp", "D");//请求类型:Q--查询(返回的是一个list),D--执行(返回的是一个结果)
		
		srcMap.put("idtftp", "01");
		srcMap.put("idtfno", "501024199112030398");
		srcMap.put("custna", "张家辉");
		requestMap.put("sys", sysMap);
		requestMap.put("comm", commMap);
		requestMap.put("request", srcMap);
		
		try {
			callClientReturnString("localhost", 8088, requestMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
