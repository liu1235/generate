package com.liuzw.generate.mapper;

import java.util.List;
import java.util.Map;

import com.liuzw.generate.bean.Column;

/**
 * @author 刘泽伟
 */
public interface ColumnMapper {
	
	/**
	 * params 中需要传入 dataBaseName 和 tableName
	 * @param params
	 * @return
	 */
	 List<Column> getTableAllColumns(Map<String,String> params);
	
	/**
	 *  params 中需要传入 dataBaseName 和 tableName
	 * @param params
	 * @return
	 */
	 List<Column> getTablePkColumns(Map<String,String> params);
	
	

}
