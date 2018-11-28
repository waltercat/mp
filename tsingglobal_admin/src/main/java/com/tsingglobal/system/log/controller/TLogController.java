package com.tsingglobal.system.log.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.tsingglobal.system.log.service.ITLogService;
import com.tsingglobal.system.log.dto.TLogDTO;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

/**
* 描述：质量问题控制层
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
@RestController
@RequestMapping(value="com.tsingglobal.system.log")
public class TLogController {

    @Autowired
    private ITLogService tLogService;

    /**
    * 描述：根据Id 查询
    * @param id  质量问题id
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
    * 描述:创建质量问题
    * @param tLogDTO  质量问题DTO
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
    * 描述：删除质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/delTLog/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") long id) throws Exception {
        
        tLogService.deleteTLog(id);
        
        CommonUtil.success( CommonUtil.getResponse(), ""+id);
    }
    
    @GetMapping(value="/getTLog/{id}")
	public void edit(@PathVariable  final Long id) throws Exception {
		
		TLogDTO tLogDTO = tLogService.loadTLog(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tLogDTO,new IntegerValueFilter()));
	}

    /**
    * 描述：更新质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/putTLog/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateTLog(@PathVariable("id") Long id,@RequestBody TLogDTO tLogDTO) throws Exception {
        
        tLogDTO.setId(id);
        
        tLogService.updateTLog(tLogDTO);
        
        CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(tLogDTO));
    }

}
