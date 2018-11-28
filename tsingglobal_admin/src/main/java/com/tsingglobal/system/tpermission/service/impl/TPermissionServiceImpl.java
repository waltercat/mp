package com.tsingglobal.system.tpermission.service.impl;
import com.tsingglobal.system.tpermission.repository.TPermissionRepository;
import com.tsingglobal.system.tpermission.service.ITPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tsingglobal.system.tpermission.dto.TPermissionDTO;
import com.tsingglobal.utils.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;

/**
* 描述：质量问题 服务实现层
* @author Ay
* @date Thu Nov 29 00:37:12 CST 2018
*/
@Service
public class TPermissionServiceImpl implements ITPermissionService {

    @Autowired
    private TPermissionRepository tPermissionRepository;

    @Override
    public TPermissionDTO loadTPermission(long id)throws Exception {
     
     	return tPermissionRepository.load(id); 
    }
    
    public void deleteTPermission(long id) throws Exception {
     
     	tPermissionRepository.delete(id); 
    }

    @Override
    public TPermissionDTO saveTPermission(final TPermissionDTO tPermissionDTO) throws Exception {
        
        if( null == tPermissionDTO ){
        	
        	return null;
        }
        
        TPermissionDTO tPermission = new TPermissionDTO();
        
        tPermission.setId(new SnowflakeIdWorker(0, 0).nextId());
        
        tPermissionRepository.save( tPermission );
        
        return loadTPermission( tPermission.getId() );
    }

    @Override
    public TPermissionDTO updateTPermission(final TPermissionDTO tPermissionDTO)throws Exception {
        
         if( (null == tPermissionDTO)  || (0 == tPermissionDTO.getId())){
        	
        	return null;
        }
        
        TPermissionDTO tPermission = loadTPermission( tPermissionDTO.getId() );
        
        BeanUtils.copyProperties(tPermission,tPermissionDTO);
        
        tPermissionRepository.update( tPermission );
        
        return tPermission;
    }
  }
