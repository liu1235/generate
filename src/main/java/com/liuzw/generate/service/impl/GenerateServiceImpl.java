package com.liuzw.generate.service.impl;

import com.alibaba.fastjson.JSON;
import com.liuzw.generate.bean.BasicData;
import com.liuzw.generate.bean.Column;
import com.liuzw.generate.bean.MessageResp;
import com.liuzw.generate.common.ErrorMsg;
import com.liuzw.generate.mapper.ColumnMapper;
import com.liuzw.generate.service.IGenerateService;
import com.liuzw.generate.utils.SystemUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘泽伟
 */

@Service
public class GenerateServiceImpl implements IGenerateService {

    private static final Logger log = Logger.getLogger(GenerateServiceImpl.class);

    private static BasicData basicData;

    private static Configuration cfg;
    /**
     * 文件输出路径
     */
    private static String path;

    /**
     * 用于存放表的字段信息到内存中
     */
    private static Map<String, List<Column>> tempMap;

    static{

        tempMap = new HashMap<String, List<Column>>();

        //文件输出路径
        path = SystemUtils.readValue("path");

        if (StringUtils.isEmpty(path)) {
            path = getPath();
        }

        basicData = new BasicData();
        //获取包的路径
        basicData.setPackagePath(SystemUtils.readValue("packagePath"));
        //获取模块
        basicData.setModule(SystemUtils.readValue("module"));
        //获取数据库名字
        basicData.setDataBaseName(SystemUtils.readValue("dataBaseName"));

        cfg = new Configuration();

        cfg.setClassForTemplateLoading(GenerateServiceImpl.class,SystemUtils.readValue("template_path"));

    }


    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public MessageResp generateEntity(String tableNames) {
        return generate(tableNames,"entity.ftl",".java","/bean");
    }

    @Override
    public MessageResp generateService(String tableNames) {
        return generate(tableNames,"service.ftl","Service.java","/service");
    }
    @Override
    public MessageResp generateServiceImpl(String tableNames) {
        return generate(tableNames,"serviceImpl.ftl","ServiceImpl.java","/service/impl");
    }
    @Override
    public MessageResp generateDao(String tableNames) {
        return generate(tableNames,"dao.ftl","Dao.java","/mapper");
    }

    @Override
    public MessageResp generateMapper(String tableNames) {
       return generate(tableNames,"mapper.ftl","Dao.xml","/mapper");
    }

    @Override
    public MessageResp generateAll(String tableNames) {
        try {
            generateEntity(tableNames);
            generateService(tableNames);
            generateServiceImpl(tableNames);
            generateDao(tableNames);
            generateMapper(tableNames);
        }catch (Exception e) {
            return new MessageResp(Boolean.FALSE.toString(), ErrorMsg.OPTION_FAIL);
        }
        return new MessageResp(Boolean.TRUE.toString(),ErrorMsg.OPTION_SUCCESS);
    }


    /**
     *
     * @param tableName     表名
     * @param templateName   模板名
     * @param suffix         输出的文件的后缀
     * @param filepath       文件的路径
     */

    private MessageResp generate(String tableName, String templateName, String suffix, String filepath){

        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(templateName)
                || StringUtils.isEmpty(suffix) || StringUtils.isEmpty(filepath)) {
            return new MessageResp(Boolean.FALSE.toString(), ErrorMsg.OPTION_FAIL);
        }

        try {

            String[] tableNames = tableName.split(",");

            for (String table : tableNames) {

                basicData.setTableName(table.toLowerCase());

                Template temp = cfg.getTemplate(templateName);

                String outFileName = basicData.getClassName() + suffix;

                String outdir = path + "/" + basicData.getPackagePath().replaceAll("\\.", "/") + "/" + basicData.getModule() + filepath;

                log.info("========outdir=====" + outdir);
                System.out.print("========outdir=====" + outdir);
                processBasicData();

                generate(basicData, temp, outFileName, outdir);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new MessageResp(Boolean.FALSE.toString(), ErrorMsg.OPTION_FAIL);
        }

        return new MessageResp(Boolean.TRUE.toString(),ErrorMsg.OPTION_SUCCESS);
    }

    /**
     * 获取表的信息
     */
    private void processBasicData(){

        String columnsKey = basicData.getDataBaseName() + "_" + basicData.getTableName() + "_columns";

        String pkColumnsKey = basicData.getDataBaseName() + "_" + basicData.getTableName() +"_pkColumns";

        if (MapUtils.isEmpty(tempMap) || MapUtils.getObject(tempMap,columnsKey) == null || MapUtils.getObject(tempMap,pkColumnsKey) == null) {

            Map<String,String> params = new HashMap<String,String>(10);
            //数据库名字
            params.put("dataBaseName", basicData.getDataBaseName());
            //表名
            params.put("tableName", basicData.getTableName());
            //数据库类型
            params.put("dataBaseType",SystemUtils.readValue("dataBaseType"));
            //表中所有字段的全部信息
            List<Column> columns = columnMapper.getTableAllColumns(params);
            //获取表的主键
            List<Column> pkColumns = columnMapper.getTablePkColumns(params);

            tempMap.put(columnsKey,columns);

            tempMap.put(pkColumnsKey,pkColumns);
        }

        //从内存中获取数据
        basicData.setColumns(tempMap.get(columnsKey));

        basicData.setPkColumns(tempMap.get(pkColumnsKey));

        log.info("=========basicData:" + JSON.toJSON(basicData));
    }


    /**
     * 通过模板生成文件
     */

    private void generate(BasicData data, Template temp,String outFileName, String outdir){
        try {
            File f  =  new File(outdir);

            if (!f.exists()) {
                f.mkdirs();
            }
            File fn  =  new File(f.getPath()  +  "\\"  +  outFileName);

            log.info("======================:" + f.getPath()  +  "\\"  +  outFileName);
            if (!fn.exists()) {
                fn.createNewFile();
            }
            FileOutputStream fos  =  new FileOutputStream(fn);

            Writer out  =  new OutputStreamWriter(fos);

            Map<String,Object> root  =  new HashMap<String,Object>(10);

            root.put("data", data);

            temp.process(root, out);

            out.flush();
            out.close();
        }catch (TemplateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *  获取输出文件的路径
     */
    private static String getPath() {
        String path = System.getProperty("user.dir") + "/out/";
        File file  =  new File(path);
        if (file.exists()) {
            file.delete();
        }
        return file.getPath();
    }
}
