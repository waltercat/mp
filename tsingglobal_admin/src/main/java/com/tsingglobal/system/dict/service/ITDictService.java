package com.tsingglobal.system.dict.service;
import com.tsingglobal.system.dict.dto.TDictDTO;
/**
* 描述：数据字典 服务实现层接口
* @author Ay
* @date Thu Nov 29 00:41:57 CST 2018
*/
public interface ITDictService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    TDictDTO loadTDict(long id)throws Exception;

    TDictDTO saveTDict(TDictDTO tDictDTO) throws Exception;

    void deleteTDict(long id) throws Exception;

    TDictDTO updateTDict(TDictDTO tDictDTO) throws Exception;
    
    
    

}