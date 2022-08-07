package com.andyadc.abatis.scripting.xmltags;

/**
 * SQL 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);
}
