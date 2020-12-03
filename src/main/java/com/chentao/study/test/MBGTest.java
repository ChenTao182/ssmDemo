package com.chentao.study.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author:Ct
 * Date: 2020/12/1
 * Time: 16:29
 */
public class MBGTest {

    // 问题记录：
    // 1.Establishing SSL connection without server's identity verification is not recommended.
    //  不建议在没有服务器身份验证的情况下建立SSL连接,根据MySQL 5.5.45+、5.6.26+和5.7.6+的要求，
    //  如果没有设置显式选项，则必须默认建立SSL连接。为了符合不使用SSL的现有应用程序，verifyServerCertificate属性被设置为“false”。
    //  您需要通过设置useSSL=false显式禁用SSL，或者设置useSSL=true并为服务器证书验证提供信任存储。
    // jdbc数据库连接路径则要这样写  jdbc:mysql://localhost:3306/ssm_crud?useSSL=false

    // 2.java.sql.SQLException: Unknown system variable 'query_cache_size'
    // 原因是mysql-connecter-java的版本过低，很显然是数据库驱动程序与数据库版本不对应
    // pom.xml中修改mysql-connecter-java的版本为本地数据库mysql的版本
    public static void main(String[] args) throws Exception {
        List<String> warning = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warning);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warning);
        myBatisGenerator.generate(null);

    }
}
