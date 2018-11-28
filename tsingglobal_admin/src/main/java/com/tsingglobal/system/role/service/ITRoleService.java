package com.tsingglobal.system.role.service;
import com.tsingglobal.system.role.dto.TRoleDTO;
/**
* 描述：质量问题 服务实现层接口
* @author Ay
* @date Thu Nov 29 00:34:17 CST 2018
*/
public interface ITRoleService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    TRoleDTO loadTRole(long id)throws Exception;

    TRoleDTO saveTRole(TRoleDTO tRoleDTO) throws Exception;

    void deleteTRole(long id) throws Exception;

    TRoleDTO updateTRole(TRoleDTO tRoleDTO) throws Exception;
    
    
    

}