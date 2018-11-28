package com.common.repository;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
* 描述：数据访问公共接口，定义数据的CRUD行为
* @author Ay
* @date Wed Nov 28 23:57:40 CST 2018
*/
@Mapper
public interface BaseRepository<T> {

	/**
	 * 加载信息
	 * id 待加载信息的ID
	 * 返回信息
	 */
	public T load( final long id);
	
	
	/**
	 * 
	    * @Title: 条件查询
	    * @Description: TODO(按条件查询信息)
	    * @param map 查询条件
	    * @return List<T>    返回信息
	 */
	public List<T> queryList(Map<String, Object> map);
	
	
	/**
	 * 
	    * @Title: 保存信息
	    * @Description: TODO(保存信息)
	    * @param  t    	待保存信息
	    * @return void    返回类型 空
	 */
	public void save(T t);

	/**
	 * 
	    * @Title: 保存信息
	    * @Description: TODO(保存信息)
	    * @param  map    待保存信息集合
	    * @return void    返回类型 空
	 */
	public void save(Map<String, Object> map);

	/**
	 * 
	    * @Title: 更新信息
	    * @Description: TODO(更新信息)
	    * @param  t    	待更新信息
	    * @return int      更新数量
	 */
	int update(T t);

	/**
	 * 
	    * @Title: 更新信息
	    * @Description: TODO(更新信息)
	    * @param  map  待更新信息
	    * @return int      更新数量
	 */
	int update(Map<String, Object> map);

	/**
	 * 
	    * @Title: 删除信息
	    * @Description: TODO(删除信息)
	    * @param  id    	待删除信息
	    * @return int      删除数量
	 */
	int delete(Object id);

	
	/**
	 * 
	    * @Title: 删除多条信息
	    * @Description: TODO(删除多条信息)
	    * @param  id    	待删除信息ID数组
	    * @return int      删除数量
	 */
	int deleteBatch(Object[] id);
}
