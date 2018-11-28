package com.tsingglobal.system.log.service;
import com.tsingglobal.system.log.dto.TLogDTO;
/**
* 描述：质量问题 服务实现层接口
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
public interface ITLogService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    TLogDTO loadTLog(long id)throws Exception;

    TLogDTO saveTLog(TLogDTO tLogDTO) throws Exception;

    void deleteTLog(long id) throws Exception;

    TLogDTO updateTLog(TLogDTO tLogDTO) throws Exception;
    
    
    

}