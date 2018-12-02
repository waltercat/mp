package com.tsingglobal.system.log.repository;
import org.apache.ibatis.annotations.Mapper;

import com.common.repository.BaseRepository;
import com.tsingglobal.system.log.dto.TLogDTO;

/**
* 描述：系统日志 Repository接口
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
@Mapper
public interface TLogRepository extends BaseRepository<TLogDTO>{
	
}
