package com.liuzw.generate.controller;


import com.liuzw.generate.bean.MessageResp;
import com.liuzw.generate.service.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘泽伟
 */
@RestController
@RequestMapping("generate")
public class GenerateController {
	
	@Autowired
	private IGenerateService generateService;

	/**
	 * 生成实体类
	 *
	 */
	@RequestMapping("generateEntity")
	public MessageResp generateEntity(String tableNames) {
		return generateService.generateEntity(tableNames);
	}

	/**
	 * 生成service接口
	 *
     */
	@RequestMapping("generateService")
	public MessageResp generateService(String tableNames){
		return generateService.generateService(tableNames);
	}

	/**
	 * 生成service实现类
	 *
	 */
	@RequestMapping("generateServiceImpl")
	public MessageResp generateServiceImpl(String tableNames){
		return generateService.generateServiceImpl(tableNames);
	}

	/**
	 * 生成dao层
	 *
	 */
	@RequestMapping("generateDao")
	public MessageResp generateDao(String tableNames){
		return generateService.generateDao(tableNames);
	}

	/**
	 * 生成mapper文件
	 *
	 */
	@RequestMapping("generateMapper")
	public MessageResp generateMapper(String tableNames){
		return generateService.generateMapper(tableNames);
	}

	/**
	 * 生成所有
	 *
	 */
	@RequestMapping("generateAll")
	public MessageResp generateAll(String tableNames){
		return generateService.generateAll(tableNames);
	}

}
