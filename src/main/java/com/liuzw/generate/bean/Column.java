package com.liuzw.generate.bean;

import com.liuzw.generate.utils.JdbcTypeUtils;
import com.liuzw.generate.utils.JavaTypeUtils;
import com.liuzw.generate.utils.StringUtility;

/**
 * @author 刘泽伟
 */
public class Column {
	
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 字段中文名
	 */
	private String columnCname;
	/**
	 * 字段属性名称
	 */
	private String propertyName;
	/**
	 * 字段属性中文名
	 */
	private String propertyCname;
	/**
	 * 字段长度
	 */
	private Integer dataLength;
	/**
	 * 字段类型
	 */
	private String dataType;
	/**
	 * 类中属性类型
	 */
	private String propertyType;
	/**
	 * 字段类型
	 */
	private String jdbcType;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName.toLowerCase();
	}

	public String getColumnCname() {
		return columnCname;
	}

	public void setColumnCname(String columnCname) {
		this.columnCname = columnCname;
	}

	public String getPropertyName() {
		this.propertyName= StringUtility.getCamelCaseString(this.columnName,false);
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyCname() {
		this.propertyCname = this.columnCname;
		return propertyCname;
	}
	public void setPropertyCname(String propertyCname) {
		this.propertyCname = propertyCname;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPropertyType() {
		propertyType = JavaTypeUtils.readValue(this.dataType.toUpperCase());
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	public String getMethodName() {
		return StringUtility.getCamelCaseString(this.columnName,true);
	}

	public String getJdbcType() {
		jdbcType = JdbcTypeUtils.readValue(this.dataType.toUpperCase());
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	
	
	
	
	
	

}
