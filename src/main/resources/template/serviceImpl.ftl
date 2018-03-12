package ${data.packagePath}.${data.module}.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ${data.packagePath}.${data.module}.dao.${data.className}Dao;
import ${data.packagePath}.${data.module}.model.${data.className};
import ${data.packagePath}.${data.module}.service.${data.className}Service;

/**
 *  ${data.className}ServiceImpl
 *
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">
@Service("${data.varName}Service")
public class ${data.className}ServiceImpl  implements I${data.className}Service {
		
		
	@Autowired
	private ${data.className}Dao ${data.varName}Dao;

	@Override
	public void insert(${data.className} ${data.varName}) {
		${data.varName}Dao.insert(${data.varName});
	}

	@Override
	public void insertSelective(${data.className} ${data.varName}) {
		${data.varName}Dao.insertSelective(${data.varName});
		
	}

	@Override
	public void delete(${pkPropertyType} ${pkProperty}) {
		${data.varName}Dao.delete(${pkProperty});
	}

	@Override
	public void updateByPrimaryKeySelective(${data.className} ${data.varName}) {
		${data.varName}Dao.updateByPrimaryKeySelective(${data.varName});
		
	}

	@Override
	public void update(${data.className} ${data.varName}) {
		${data.varName}Dao.update(${data.varName});
		
	}

	@Override
	public ${data.className} selectByPrimaryKey(${pkPropertyType} ${pkProperty}) {
		return ${data.varName}Dao.selectByPrimaryKey(${pkProperty});
	}

}