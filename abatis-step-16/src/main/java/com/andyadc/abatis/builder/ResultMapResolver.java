package com.andyadc.abatis.builder;

import com.andyadc.abatis.mapping.ResultMap;
import com.andyadc.abatis.mapping.ResultMapping;

import java.util.List;

/**
 * 结果映射解析器
 */
public class ResultMapResolver {

    private final MapperBuilderAssistant assistant;
    private final String id;
    private final Class<?> type;
    private final List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.resultMappings);
    }
}
