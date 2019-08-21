package com.lfm.test;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class MybatisGenerator {
    public static void main(String[] args) throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("generatorConfig.xml");
        log.info(configFile.getAbsolutePath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static class EmptyGenerator implements CommentGenerator{

        @Override
        public void addConfigurationProperties(Properties properties) {

        }

        @Override
        public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        }

        @Override
        public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

        }

        @Override
        public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        }

        @Override
        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

        }

        @Override
        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

        }

        @Override
        public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

        }

        @Override
        public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        }

        @Override
        public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        }

        @Override
        public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

        }

        @Override
        public void addJavaFileComment(CompilationUnit compilationUnit) {

        }

        @Override
        public void addComment(XmlElement xmlElement) {

        }

        @Override
        public void addRootComment(XmlElement rootElement) {

        }

        @Override
        public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

        }

        @Override
        public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

        }

        @Override
        public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

        }

        @Override
        public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

        }

        @Override
        public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

        }
    }

    public static class MySQLCommentGenerator extends EmptyGenerator {

        private Properties properties;

        public MySQLCommentGenerator() {
            properties = new Properties();
        }

        @Override
        public void addConfigurationProperties(Properties properties) {
            // 获取自定义的 properties
            this.properties.putAll(properties);
        }

        @Override
        public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
            String author = properties.getProperty("author");
            String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
            SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

            // 获取表注释
            String remarks = introspectedTable.getRemarks();

            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * " + remarks);
            topLevelClass.addJavaDocLine(" *");
            topLevelClass.addJavaDocLine(" * @author " + author);
            topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
            topLevelClass.addJavaDocLine(" */");
        }

        @Override
        public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
            // 获取列注释
            String remarks = introspectedColumn.getRemarks();
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + remarks);
            field.addJavaDocLine(" */");
        }
    }
}
