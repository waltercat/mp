package com.tsingglobal.system.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tsingglobal.system.role.dto.TRoleDTO;
import com.tsingglobal.system.role.service.ITRoleService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

/**
* 描述：质量问题控制层
* @author Ay
* @date Thu Nov 29 00:34:17 CST 2018
*/
@RestController
@RequestMapping(value="com.tsingglobal.system.role")
public class TRoleController {

    @Autowired
    private ITRoleService tRoleService;

    /**
    * 描述：根据Id 查询
    * @param id  质量问题id
    */
    @RequestMapping(value = "/getTRole/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void findById(@PathVariable("id") Long id) throws Exception {
    
        TRoleDTO tRoleDTO = tRoleService.loadTRole(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tRoleDTO,new IntegerValueFilter()));
    }
    
     /**
    * 描述：创建信息
    * 
    */
	@GetMapping(value="/getNewTRole")
	public void create() throws Exception{
		CommonUtil.success(CommonUtil.getResponse(), 
				JSON.toJSONString( new TRoleDTO(),	new IntegerValueFilter() ));
	}

    /**
    * 描述:创建质量问题
    * @param tRoleDTO  质量问题DTO
    */
    @RequestMapping(value = "/putTRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody TRoleDTO tRoleDTO) throws Exception {
        
        if( CommonUtil.isEmpty(tRoleDTO)  ) {
			
			CommonUtil.error(CommonUtil.getResponse(),"缺少要保存的数据！");
			
			return ;
		}
		
		TRoleDTO tRoleDTO1 = tRoleService.saveTRole( tRoleDTO );
		
		CommonUtil.success(CommonUtil.getResponse(), 
												JSON.toJSONString( tRoleDTO1,	new IntegerValueFilter() ));
    }

    /**
    * 描述：删除质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/delTRole/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") long id) throws Exception {
        
        tRoleService.deleteTRole(id);
        
        CommonUtil.success( CommonUtil.getResponse(), ""+id);
    }
    
    @GetMapping(value="/getTRole/{id}")
	public void edit(@PathVariable  final Long id) throws Exception {
		
		TRoleDTO tRoleDTO = tRoleService.loadTRole(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tRoleDTO,new IntegerValueFilter()));
	}

    /**
    * 描述：更新质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/putTRole/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateTRole(@PathVariable("id") Long id,@RequestBody TRoleDTO tRoleDTO) throws Exception {
        
        tRoleDTO.setId(id);
        
        tRoleService.updateTRole(tRoleDTO);
        
        CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(tRoleDTO));
    }

}
