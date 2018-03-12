package com.liuzw.generate.service;

import com.liuzw.generate.bean.MessageResp;

/**
 *@author 刘泽伟
 *
 *  生成各种java文件，如service mapper。
 */
public interface IGenerateService {


    /**
     * 生成实体类
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateEntity(String tableNames);

    /**
     * 生成Servicr类
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateService(String tableNames);

    /**
     * 生成Servicr实现类
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateServiceImpl(String tableNames);

    /**
     * 生成dao
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateDao(String tableNames);

    /**
     * 生成Mapper文件
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateMapper(String tableNames);

    /**
     * 生成所有文件
     *
     * @param tableNames 表名
     * @return           MessageResp
     */
    MessageResp generateAll(String tableNames);
}
