package com.hys.exam.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class FreeMarkerUtils {
    /**
     * 生成静态页面的核心数据
     *
     * @param file
     * @param articleData
     * @param templateName
     * @throws IOException
     */
    public static void generateHtml(File file, Map<String, Object> articleData,String templatePath, String templateName) throws IOException {
        // 指定版本号
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        // 获得模板文件路径

        // 设置模板目录
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        // 设置默认编码格式
        cfg.setDefaultEncoding("UTF-8");
        // 从设置的目录中获得模板
        Template template = cfg.getTemplate(templateName);
        // 合并模板和数据模型
        try {
            // 将数据与模板渲染的结果写入文件中
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            template.process(articleData, writer);
            writer.flush();
            articleData.clear();
            writer.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
