package cn.spring.mvc.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.spring.mvc.base.tools.BaseTool;
import cn.spring.mvc.comm.tools.SequenceTool;
import cn.spring.mvc.comm.util.CommUtil;
import cn.spring.mvc.server.service.CoreServerImpl;
import cn.spring.mvc.server.tools.ServerTool;

/**
 * @author LiuTao @date 2018年6月13日 下午10:32:13
 * @ClassName: SocketHandlerImpl 
 * @Description: 接收到客户端发送的消息后,登记交易记录表和执行交易
 * 接口定义表字段:交易码,交易所在类,交易对应方法.....
 * 交易记录表字段:交易码,执行时间,执行结果,信息,输入报文,输出报文
 */
@SuppressWarnings({ "unchecked", "unused" })
public class SocketHandlerImpl {
	private static String ERROR = "ERROR";
	private static String SUCCESS = "SUCCESS";
	
	public static String callInterface(String jsonStr){
		System.out.println("SocketHandlerImpl.callInterface");
		String returnString = "";
		String responseStatus = "";
		String responseMesage = "";
		String responseSerial = BaseTool.getSerial();
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> requestSysMap = new HashMap<String, Object>();
		Map<String, Object> requestCommMap = new HashMap<String, Object>();
		Map<String, Object> requestDataMap = new HashMap<String, Object>();
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> responseSysMap = new HashMap<String, Object>();
		Map<String, Object> responseCommMap = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		
		String corpno = "";
		String servtp = "";
		String servno = "";
		String serial = "";

		String corecd = "";
		String asktyp = "";
		String status = "";
		String mesage = "";

		try {
			requestMap = ServerTool.praseRequestStringMap(jsonStr);
			
			requestSysMap = (Map<String, Object>) requestMap.get("sys");
			requestCommMap = (Map<String, Object>) requestMap.get("comm");
			requestDataMap = (Map<String, Object>) requestMap.get("request");
			
			corpno = (String) requestSysMap.get("corpno");//机构
			servtp = (String) requestSysMap.get("servtp");//渠道类型
			servno = (String) requestSysMap.get("servno");//渠道号
			serial = (String) requestSysMap.get("serial");//流水号

			corecd = (String) requestCommMap.get("corecd");//交易码,接口定义表中的唯一索引
			asktyp = (String) requestCommMap.get("asktyp");//请求类型:Q--查询类,D--执行类
			status = (String) requestCommMap.get("status");
			mesage = (String) requestCommMap.get("mesage");

			
			responseSysMap.put("corpno", corpno);
			responseSysMap.put("servtp", servtp);
			responseSysMap.put("servno", servno);
			responseSysMap.put("serial", responseSerial);
			if(CommUtil.isNull(corecd)){
				responseStatus = ERROR;
				responseMesage = "交易码corecd不能为空";
				
				responseCommMap.put("corecd", corecd);
				responseCommMap.put("asktyp", asktyp);
				responseCommMap.put("status", responseStatus);
				responseCommMap.put("mesage", responseMesage);

				responseMap.put("sys", responseSysMap);
				responseMap.put("comm", responseCommMap);
			}else if(CommUtil.isNull(asktyp)){
				responseStatus = ERROR;
				responseMesage = "请求类型asktyp不能为空";
				
				responseCommMap.put("corecd", corecd);
				responseCommMap.put("asktyp", asktyp);
				responseCommMap.put("status", responseStatus);
				responseCommMap.put("mesage", responseMesage);
				
				responseMap.put("sys", responseSysMap);
				responseMap.put("comm", responseCommMap);
			}else{
				//根据接口文件将接口的input内容从requestDataMap中取出来,并赋值给掉用的方法
				//还是通过反射做,但是发射如何给方法传参数需要百度 
				//用xml配置报文不太好,因为在接口处理后,根据xml里面的节点进行后续组装responseMap时,
				//必须要获取节点的具体字段才能准确的将值放进对应的key里面
	//			List<String> requestStrList = ServerTool.getRequestElementNameStrLits(corecd);
	//			for (String string : requestStrList) {//
	//				
	//			}
				//迭代器获取requestDataMap中的参数作为传递的数据
				for (Entry<String, Object> entry : requestDataMap.entrySet()) {
					
				}
				//将参数传入需要执行的方法签名如何实现------???未知???
				Map<String, Object> rstMap = new HashMap<String, Object>();//调用接口后得到的map
				//根据接口类型(asktyp)选择返回接口response的抓成方式
				try {
					rstMap = CoreServerImpl.openAccount((String) requestDataMap.get("idtftp"), (String) requestDataMap.get("idtfno"), (String) requestDataMap.get("custna"));
//					rstMap.put("custno", SequenceTool.getSequence("USER"));
					if("Q".equals(asktyp)){//Q 
						responseDataMap.put("count", rstMap.size());
						responseDataMap.put("data", rstMap);
					}else {//D
						responseDataMap.put("data", rstMap);
					}
					responseStatus = SUCCESS;
					responseMesage = "请求成功";
					responseCommMap.put("corecd", corecd);
					responseCommMap.put("asktyp", asktyp);
					responseCommMap.put("status", responseStatus);
					responseCommMap.put("mesage", responseMesage);
				} catch (Exception e) {
					responseStatus = ERROR;
					responseMesage = e.getMessage();
					responseCommMap.put("corecd", corecd);
					responseCommMap.put("asktyp", asktyp);
					responseCommMap.put("status", responseStatus);
					responseCommMap.put("mesage", responseMesage);
				}
				//调用的具体执行方法都必须返回的是一个map
	//			responseMap = 
				
				responseMap.put("sys", responseSysMap);
				responseMap.put("comm", responseCommMap);
				responseMap.put("response", responseDataMap);
			}
		} catch (Exception e) {
			responseStatus = ERROR;
			responseMesage = e.getMessage();
			responseSysMap.put("corpno", corpno);
			responseSysMap.put("servtp", servtp);
			responseSysMap.put("servno", servno);
			responseSysMap.put("serial", responseSerial);
			
			responseCommMap.put("corecd", corecd);
			responseCommMap.put("asktyp", asktyp);
			responseCommMap.put("status", responseStatus);
			responseCommMap.put("mesage", responseMesage);
			
			responseMap.put("sys", responseSysMap);
			responseMap.put("comm", responseCommMap);
			
		}
		returnString = ServerTool.praseMapToString(responseMap);
		return returnString;
	}
}
