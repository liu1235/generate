package ${data.packagePath}.${data.module}.model;


/**
 * TABLE_NAME:(${data.tableName})
 *
 */

public class ${data.className} {

<#list data.columns as c>
    /**
     * ${c.propertyCname?if_exists}
	 */
	private ${c.propertyType} ${c.propertyName};
</#list>	
	
<#list data.columns as c>
    
	public ${c.propertyType} get${c.methodName}() {
		return ${c.propertyName};
	}
    
	public void set${c.methodName}(${c.propertyType} ${c.propertyName}) {
		this.${c.propertyName} = ${c.propertyName};
	}
</#list>
	

}