package com.buschmais.jqassistant.plugin.json.parser;

import java.io.InputStream;
import java.util.Collection;

import com.buschmais.jqassistant.plugin.json.impl.parser.JSONLexer;
import com.buschmais.jqassistant.plugin.json.impl.parser.JSONParser;
import com.buschmais.jqassistant.plugin.json.impl.scanner.ConfiguredJSONLexer;
import com.buschmais.jqassistant.plugin.json.impl.scanner.ConfiguredJSONParser;

import org.antlr.v4.runtime.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ConfiguredJSONParserWithValidFilesIT {

    private String pathToJSONFile;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return DataProvider.validOwnExamples();
    }

    public ConfiguredJSONParserWithValidFilesIT(String path) {
        pathToJSONFile = path;
    }

    @Test
    public void canParseValidJSONFile() throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream(pathToJSONFile)) {
            JSONLexer l = new ConfiguredJSONLexer(CharStreams.fromStream(inputStream), pathToJSONFile);
            JSONParser p = new ConfiguredJSONParser(new CommonTokenStream(l), pathToJSONFile);
            p.document();
        }
    }

 }
