package com.tsingglobal.system.dict.controller;

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

import com.tsingglobal.system.dict.service.ITDictService;
import com.tsingglobal.system.dict.dto.TDictDTO;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

/**
* 描述：数据字典控制层
* @author Ay
* @date Thu Nov 29 00:41:57 CST 2018
*/
@RestController
@RequestMapping(value="com.tsingglobal.system.dict")
public class TDictController {

    @Autowired
    private ITDictService tDictService;

    /**
    * 描述：根据Id 查询
    * @param id  数据字典id
    */
    @RequestMapping(value = "/getTDict/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void findById(@PathVariable("id") Long id) throws Exception {
    
        TDictDTO tDictDTO = tDictService.loadTDict(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tDictDTO,new IntegerValueFilter()));
    }
    
     /**
    * 描述：创建信息
    * 
    */
	@GetMapping(value="/getNewTDict")
	public void create() throws Exception{
		CommonUtil.success(CommonUtil.getResponse(), 
				JSON.toJSONString( new TDictDTO(),	new IntegerValueFilter() ));
	}

    /**
    * 描述:创建数据字典
    * @param tDictDTO  数据字典DTO
    */
    @RequestMapping(value = "/putTDict", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody TDictDTO tDictDTO) throws Exception {
        
        if( CommonUtil.isEmpty(tDictDTO)  ) {
			
			CommonUtil.error(CommonUtil.getResponse(),"缺少要保存的数据！");
			
			return ;
		}
		
		TDictDTO tDictDTO1 = tDictService.saveTDict( tDictDTO );
		
		CommonUtil.success(CommonUtil.getResponse(), 
												JSON.toJSONString( tDictDTO1,	new IntegerValueFilter() ));
    }

    /**
    * 描述：删除数据字典
    * @param id 数据字典id
    */
    @RequestMapping(value = "/delTDict/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") long id) throws Exception {
        
        tDictService.deleteTDict(id);
        
        CommonUtil.success( CommonUtil.getResponse(), ""+id);
    }
    
    @GetMapping(value="/getTDict/{id}")
	public void edit(@PathVariable  final Long id) throws Exception {
		
		TDictDTO tDictDTO = tDictService.loadTDict(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tDictDTO,new IntegerValueFilter()));
	}

    /**
    * 描述：更新数据字典
    * @param id 数据字典id
    */
    @RequestMapping(value = "/putTDict/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateTDict(@PathVariable("id") Long id,@RequestBody TDictDTO tDictDTO) throws Exception {
        
        tDictDTO.setId(id);
        
        tDictService.updateTDict(tDictDTO);
        
        CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(tDictDTO));
    }

}
