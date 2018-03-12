package com.liuzw.generate.bean;

import com.liuzw.generate.utils.StringUtility;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author 刘泽伟
 */
public class BasicData {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 类变量名
	 */
	private String varName;
	/**
	 * 数据库名
	 */
	private String dataBaseName;
	/**
	 * 字段列(包含主键)
	 */
	private List<Column> columns;
	/**
	 * 主键列表
	 */
	private List<Column> pkColumns;
	/**
	 * 包路径
	 */
	private String packagePath;
	/**
	 * 模块名称
	 */
	private String module;
	
	
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		this.className = StringUtility.getCamelCaseString(this.tableName, true);
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getVarName() {
		this.varName = StringUtility.getCamelCaseString(this.tableName, false);
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}

	public List<Column> getPkColumns() {
		return pkColumns;
	}

	public void setPkColumns(List<Column> pkColumns) {
		this.pkColumns = pkColumns;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getDataBaseName() { return dataBaseName; }

	public void setDataBaseName(String dataBaseName) { this.dataBaseName = dataBaseName; }
}
