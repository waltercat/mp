package com.tsingglobal.utils;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tsingglobal.TsingglobalAdminApplication;
import com.tsingglobal.system.log.dto.TLogDTO;
import com.tsingglobal.system.log.service.ITLogService;
import com.tsingglobal.system.user.domain.UserModel;

public class CommonUtil {
	
	public static HttpServletRequest getRequest() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpServletResponse getResponse() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static boolean isEmpty(final String value) {

		return StringUtils.isEmpty(value);
	}

	public static boolean isEmpty(final Object value) {

		return (null == value);
	}
	
	public static String dateToStr( final Date date) {
		
		return dateToStr( date, "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static String dateToStr( final Date date, final String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 
	 * @Title: genarateID 生成ID
	 * @Description: TODO(基于Twitter的Snowflake算法，生成符合时间趋势的ID.)
	 * @return long 返回长整型ID
	 */
	public static long genarateID() {


		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0,0);

		return idWorker.nextId();
	}

	public static String MD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	public static void sendJsonData(HttpServletResponse response, String data) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}
	
	public static void success( final HttpServletResponse response, final String data) throws Exception {
		
		success( response, data, "操作完成！");
	}
	
	public static void success( final HttpServletResponse response, final String data, final String successMsg) throws Exception {
		JSONObject errorInfo = new JSONObject();
		errorInfo.put("status", true);
		errorInfo.put("code", 200);
		errorInfo.put("message", successMsg);
		errorInfo.put("data", data);
		sendJsonData( response, errorInfo.toJSONString());
	}
	
	public static void error(HttpServletResponse response, String errorMsg) throws Exception {
		JSONObject errorInfo = new JSONObject();
		errorInfo.put("status", false);
		errorInfo.put("code", 404);
		errorInfo.put("message", errorMsg);
		errorInfo.put("data", null);
		sendJsonData( response, errorInfo.toJSONString());
	}
	
	public static String getOrgCode( final String orgCode) {
		final int[] indexArrays = {2,4,8,12,16,20,24,28,32};
		
		for( int i = 0 ; i < indexArrays.length ; i++) {
			
			if( orgCode.substring(indexArrays[i]).equals( Constants.ORG_ROOT_CODE.substring(indexArrays[i]))) {
				
				return orgCode.substring(0, indexArrays[i]);
			}
			
		}
		return null;
	}
	
	public static String getPermissionCode( final String permissionCode) {
		if( CommonUtil.isEmpty(permissionCode) ) {
			
			return null;
		}
		
		final int[] indexArrays = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32};
		
		for( int i = 0 ; i < indexArrays.length ; i++) {
			
			if( permissionCode.substring(indexArrays[i]).equals( Constants.PERMISSION_ROOT_CODE.substring(indexArrays[i]))) {
				
				return permissionCode.substring(0, indexArrays[i]);
			}
			
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getParentOrgCode
	    * @Description: TODO(按组织机构级别获取机构编码的有效部分，主要用于模糊查询)
	    * @param @param orgCode	组织机构编码
	    * @param @param orgLevel	组织机构级别
	    * @param @return    参数			机构编码的有效部分
	    * @return String    返回类型
	 */
	public static String getParentOrgCode( final String orgCode, final String orgLevel) {
		
		if( CommonUtil.isEmpty(orgCode) ) {
			
			return null;
		}
		
		String 	code = null;
		
		int iOrgLevel = ( CommonUtil.isEmpty(orgLevel) ) ? 0 : Integer.parseInt(orgLevel);
		
		if( iOrgLevel > -1 ) {
					
			switch( iOrgLevel ) {
				case 0:
					code = orgCode.substring(0,2);
					break;
				case 1:
					code = orgCode.substring(0,4);
					break;
				case 2:
					code = orgCode.substring(0,8);
					break;
				case 3:
					code = orgCode.substring(0,12);
					break;
				case 4:
					code = orgCode.substring(0,16);
					break;
				case 5:
					code = orgCode.substring(0,20);
					break;
				case 6:
					code = orgCode.substring(0,24);
					break;
				case 7:
					code = orgCode.substring(0,28);
					break;
				case 8:
					code = orgCode.substring(0,32);
					break;	
			}
			
		}
		return code;
	}
	
	public static void log(final String module, final String op) throws Exception {
		
		log( module, op , null);
	}
	
	public static void log(final String module, final String op, final String userName) throws Exception {
		
		TLogDTO logDTO = new TLogDTO();
		
		logDTO.setId( CommonUtil.genarateID() );
		
		logDTO.setF_org_id(0);
		
		logDTO.setF_org_name("");
		
		logDTO.setF_user_id(0);
		
		logDTO.setF_user_name( (CommonUtil.isEmpty(userName)) ? CommonUtil.getCurUser().getUserName() : userName );
		
		logDTO.setF_log_time( new java.sql.Time(Calendar.getInstance().getTimeInMillis()));
		
		logDTO.setF_log_name(module);
		
		logDTO.setF_log_before(op);
		
//		tLogService.saveTLog(logDTO);
		
		TsingglobalAdminApplication.cac.getBean(ITLogService.class).saveTLog(logDTO);
	}
	
	public static UserModel getCurUser() {
		
		if( null == CommonUtil.getRequest().getSession().getAttribute("curUser") ) {
			
			return null;
		}
		
		return (UserModel)CommonUtil.getRequest().getSession().getAttribute("curUser");
	}
	
//	public static void main(String[] args) {
//		
//		final String orgCode ="01010001000100000000000000000000";
//		
//		CommonUtil.getOrgCode(orgCode);
//		
//	}

//	@Autowired
//    private static ITLogService tLogService;
}
