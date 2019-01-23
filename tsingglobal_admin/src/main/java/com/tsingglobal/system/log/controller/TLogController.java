package com.tsingglobal.system.log.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsingglobal.system.log.dto.TLogDTO;
import com.tsingglobal.system.log.service.ITLogService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

/**
* 描述：系统日志控制层
* @author walter
* @date Thu Nov 29 00:39:27 CST 2018
*/
@RestController
@RequestMapping(value="/system/log")
public class TLogController {

    @Autowired
    private ITLogService tLogService;
    
    @GetMapping(value="/getSysLogByPage")
	public void listSysLog( final int start, final int length, final int draw) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		
		PageHelper.startPage(start/length+1, length);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("f_log_time_begin", request.getParameter("f_log_time_begin"));
		map.put("f_log_time_end", request.getParameter("f_log_time_end"));
		
		PageInfo<TLogDTO> pageInfo = new PageInfo<TLogDTO>( tLogService.listSysLog(map) );
		
		JSONObject obj = new JSONObject();
		obj.put("draw", draw);
		obj.put("recordsTotal", pageInfo.getTotal());
		obj.put("recordsFiltered", pageInfo.getTotal());
		obj.put("data", JSONArray.parseArray( JSON.toJSONString(pageInfo.getList(),new IntegerValueFilter())));
		
		CommonUtil.sendJsonData(response, obj.toJSONString());
	}

    /**
    * 描述：根据Id 查询
    * @param id  系统日志id
    */
    @RequestMapping(value = "/getTLog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void findById(@PathVariable("id") Long id) throws Exception {
    
        TLogDTO tLogDTO = tLogService.loadTLog(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tLogDTO,new IntegerValueFilter()));
    }
    
     /**
    * 描述：创建信息
    * 
    */
	@GetMapping(value="/getNewTLog")
	public void create() throws Exception{
		CommonUtil.success(CommonUtil.getResponse(), 
				JSON.toJSONString( new TLogDTO(),	new IntegerValueFilter() ));
	}

    /**
    * 描述:创建系统日志
    * @param tLogDTO  系统日志DTO
    */
    @RequestMapping(value = "/putTLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody TLogDTO tLogDTO) throws Exception {
        
        if( CommonUtil.isEmpty(tLogDTO)  ) {
			
			CommonUtil.error(CommonUtil.getResponse(),"缺少要保存的数据！");
			
			return ;
		}
		
		TLogDTO tLogDTO1 = tLogService.saveTLog( tLogDTO );
		
		CommonUtil.success(CommonUtil.getResponse(), 
												JSON.toJSONString( tLogDTO1,	new IntegerValueFilter() ));
    }

    /**
    * 描述：删除系统日志
    * @param id 系统日志id
    */
    @RequestMapping(value = "/delTLog/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") long id) throws Exception {
        
        tLogService.deleteTLog(id);
        
        CommonUtil.success( CommonUtil.getResponse(), ""+id);
    }
    
    @DeleteMapping(value="/delTLogs")
	public void deleteByIds( @RequestBody   String logIDS , final HttpServletResponse response) throws Exception {
		
		logIDS = (logIDS.endsWith(",")) ? logIDS.substring(0, logIDS.length()-1) : logIDS;
		
		String[] logIDSArray = logIDS.split(",");
		Long[] lLogIDS = new Long[logIDSArray.length];
		
		int count = 0 ; 
		for( String id : logIDSArray) {
			lLogIDS[count++] = Long.parseLong(id);
		}
		
		tLogService.deleteTLogs(lLogIDS);
		
		CommonUtil.success(response, ""+logIDS);
	}
    
    @GetMapping(value="/getTLog/{id}")
	public void edit(@PathVariable  final Long id) throws Exception {
		
		TLogDTO tLogDTO = tLogService.loadTLog(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tLogDTO,new IntegerValueFilter()));
	}

    /**
    * 描述：更新系统日志
    * @param id 系统日志id
    */
    @RequestMapping(value = "/putTLog/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateTLog(@PathVariable("id") Long id,@RequestBody TLogDTO tLogDTO) throws Exception {
        
        tLogDTO.setId(id);
        
        tLogService.updateTLog(tLogDTO);
        
        CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(tLogDTO));
    }

}
