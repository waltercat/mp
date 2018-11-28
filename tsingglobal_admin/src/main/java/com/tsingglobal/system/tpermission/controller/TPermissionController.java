package com.tsingglobal.system.tpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tsingglobal.system.tpermission.dto.TPermissionDTO;
import com.tsingglobal.system.tpermission.service.ITPermissionService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

/**
* 描述：质量问题控制层
* @author Ay
* @date Thu Nov 29 00:37:12 CST 2018
*/
@RestController
@RequestMapping(value="com.tsingglobal.system.tpermission")
public class TPermissionController {

    @Autowired
    private ITPermissionService tPermissionService;

    /**
    * 描述：根据Id 查询
    * @param id  质量问题id
    */
    @RequestMapping(value = "/getTPermission/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void findById(@PathVariable("id") Long id) throws Exception {
    
        TPermissionDTO tPermissionDTO = tPermissionService.loadTPermission(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tPermissionDTO,new IntegerValueFilter()));
    }
    
     /**
    * 描述：创建信息
    * 
    */
	@GetMapping(value="/getNewTPermission")
	public void create() throws Exception{
		CommonUtil.success(CommonUtil.getResponse(), 
				JSON.toJSONString( new TPermissionDTO(),	new IntegerValueFilter() ));
	}

    /**
    * 描述:创建质量问题
    * @param tPermissionDTO  质量问题DTO
    */
    @RequestMapping(value = "/putTPermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody TPermissionDTO tPermissionDTO) throws Exception {
        
        if( CommonUtil.isEmpty(tPermissionDTO)  ) {
			
			CommonUtil.error(CommonUtil.getResponse(),"缺少要保存的数据！");
			
			return ;
		}
		
		TPermissionDTO tPermissionDTO1 = tPermissionService.saveTPermission( tPermissionDTO );
		
		CommonUtil.success(CommonUtil.getResponse(), 
												JSON.toJSONString( tPermissionDTO1,	new IntegerValueFilter() ));
    }

    /**
    * 描述：删除质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/delTPermission/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") long id) throws Exception {
        
        tPermissionService.deleteTPermission(id);
        
        CommonUtil.success( CommonUtil.getResponse(), ""+id);
    }
    
    @GetMapping(value="/getTPermission/{id}")
	public void edit(@PathVariable  final Long id) throws Exception {
		
		TPermissionDTO tPermissionDTO = tPermissionService.loadTPermission(id);
        
        CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(tPermissionDTO,new IntegerValueFilter()));
	}

    /**
    * 描述：更新质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/putTPermission/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateTPermission(@PathVariable("id") Long id,@RequestBody TPermissionDTO tPermissionDTO) throws Exception {
        
        tPermissionDTO.setId(id);
        
        tPermissionService.updateTPermission(tPermissionDTO);
        
        CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(tPermissionDTO));
    }

}
