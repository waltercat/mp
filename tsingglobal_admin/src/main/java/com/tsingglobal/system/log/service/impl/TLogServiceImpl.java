package com.tsingglobal.system.log.service.impl;
import com.tsingglobal.system.log.repository.TLogRepository;
import com.tsingglobal.system.log.service.ITLogService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tsingglobal.system.log.dto.TLogDTO;
import com.tsingglobal.utils.SnowflakeIdWorker;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
* 描述：质量问题 服务实现层
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
@Service
public class TLogServiceImpl implements ITLogService {

    @Autowired
    private TLogRepository tLogRepository;

    @Override
    public TLogDTO loadTLog(long id)throws Exception {
     
     	return tLogRepository.load(id); 
    }
    
    public void deleteTLog(long id) throws Exception {
     
     	tLogRepository.delete(id); 
    }

    @Override
    public TLogDTO saveTLog(final TLogDTO tLogDTO) throws Exception {
        
        if( null == tLogDTO ){
        	
        	return null;
        }
        
        TLogDTO tLog = new TLogDTO();
        
        tLog.setId(new SnowflakeIdWorker(0, 0).nextId());
        
        tLogRepository.save( tLog );
        
        return loadTLog( tLog.getId() );
    }

    @Override
    public TLogDTO updateTLog(final TLogDTO tLogDTO)throws Exception {
        
         if( (null == tLogDTO)  || (0 == tLogDTO.getId())){
        	
        	return null;
        }
        
        TLogDTO tLog = loadTLog( tLogDTO.getId() );
        
        BeanUtils.copyProperties(tLog,tLogDTO);
        
        tLogRepository.update( tLog );
        
        return tLog;
    }

	@Override
	public List<TLogDTO> listSysLog( final Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub queryList
		return tLogRepository.queryList(map);
	}

	@Override
	public void deleteTLogs(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
		tLogRepository.deleteBatch(ids);
	}
  }
