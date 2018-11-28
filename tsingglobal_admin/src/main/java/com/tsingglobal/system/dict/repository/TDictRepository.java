package com.tsingglobal.system.dict.repository;
import com.tsingglobal.system.dict.dto.TDictDTO;
import com.common.repository.BaseRepository;
import org.apache.ibatis.annotations.Mapper;

/**
* 描述：数据字典 Repository接口
* @author Ay
* @date Thu Nov 29 00:41:57 CST 2018
*/
@Mapper
public interface TDictRepository extends BaseRepository<TDictDTO>{

}
