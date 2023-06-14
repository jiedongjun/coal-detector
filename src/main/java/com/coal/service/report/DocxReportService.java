package com.coal.service.report;

import com.coal.config.ApplicationProperties;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.util.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class DocxReportService {

    private final ApplicationProperties applicationProperties;

    public DocxReportService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public byte[] fillWordTemplate(File wordTempFile, ObjectNode param) throws Exception {
        FileInputStream fis = new FileInputStream(wordTempFile);
        XWPFDocument document = new XWPFDocument(fis);
        List<XWPFTable> tables = document.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    String key = null;
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        for (CTBookmark bookmark : paragraph.getCTP().getBookmarkStartList()) {
                            key = bookmark.getName();
                        }
                    }
                    if (key != null && param.has(key) && param.get(key) != null && !"null".equals(param.get(key).asText())) {
                        cell.setText(param.get(key).asText());
                    }
                }
            }
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.write(outputStream);
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();
        document.close();
        return bytes;
    }

    public File getTemplateFile(String key) {
        //TODO 添加模板管理功能  用户可上传模板  通过 key 获取docx模板文件
        try {
            return ResourceUtils.getFile(applicationProperties.getReportTemplatePath() + (key.endsWith(".docx") ? key : key + ".docx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
