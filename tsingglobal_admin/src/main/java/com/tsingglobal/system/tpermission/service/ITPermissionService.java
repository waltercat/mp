package com.tsingglobal.system.tpermission.service;
import com.tsingglobal.system.tpermission.dto.TPermissionDTO;
/**
* 描述：质量问题 服务实现层接口
* @author Ay
* @date Thu Nov 29 00:37:12 CST 2018
*/
public interface ITPermissionService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    TPermissionDTO loadTPermission(long id)throws Exception;

    TPermissionDTO saveTPermission(TPermissionDTO tPermissionDTO) throws Exception;

    void deleteTPermission(long id) throws Exception;

    TPermissionDTO updateTPermission(TPermissionDTO tPermissionDTO) throws Exception;
    
    
    

}