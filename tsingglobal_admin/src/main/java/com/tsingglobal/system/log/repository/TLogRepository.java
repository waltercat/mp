package com.tsingglobal.system.log.repository;
import com.tsingglobal.system.log.dto.TLogDTO;
import com.common.repository.BaseRepository;
import org.apache.ibatis.annotations.Mapper;

/**
* 描述：质量问题 Repository接口
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
@Mapper
public interface TLogRepository extends BaseRepository<TLogDTO>{

}
