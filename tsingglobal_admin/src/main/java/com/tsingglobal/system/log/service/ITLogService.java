package com.tsingglobal.system.log.service;
import java.util.List;
import java.util.Map;

import com.tsingglobal.system.log.dto.TLogDTO;
/**
* 描述：系统日志 服务实现层接口
* @author waltercat
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
    
    void deleteTLogs(Long[] ids) throws Exception;

    TLogDTO updateTLog(TLogDTO tLogDTO) throws Exception;

    List<TLogDTO> listSysLog(final Map<String,Object> map) throws Exception;
}