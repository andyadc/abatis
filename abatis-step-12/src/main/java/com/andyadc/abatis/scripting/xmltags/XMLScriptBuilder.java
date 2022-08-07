package com.andyadc.abatis.scripting.xmltags;

import com.andyadc.abatis.builder.BaseBuilder;
import com.andyadc.abatis.mapping.SqlSource;
import com.andyadc.abatis.scripting.defaults.RawSqlSource;
import com.andyadc.abatis.session.Configuration;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * XML脚本构建器
 */
public class XMLScriptBuilder extends BaseBuilder {

    private final Element element;
    private final Class<?> parameterType;
    private boolean isDynamic;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode() {
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(configuration, rootSqlNode, parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element) {
        List<SqlNode> contents = new ArrayList<>();
        // element.getText 拿到 SQL
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }
}
