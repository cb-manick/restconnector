package org.cb.restconnector.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.Assert;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.exception.ResolutionException;
import org.openapi4j.core.validation.ValidationException;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.model.v3.Components;
import org.openapi4j.parser.model.v3.OpenApi3;
import org.openapi4j.parser.model.v3.Path;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class OpenApiParserTest {
    @Test
    public void testOpenApiParser() throws MalformedURLException, ResolutionException, ValidationException, EncodeException {

        OpenApi3 api = new OpenApi3Parser().parse(new URL("https://petstore3.swagger.io/api/v3/openapi.json"), false);
        //"https://petstore3.swagger.io/api/v3/openapi.json", null, null);

        Components components = api.getComponents();//components
        Map<String, Path> paths = api.getPaths();

        for ( String path : paths.keySet()) {
            Path pathObj = paths.get(path);
            JsonNode jsonNode = pathObj.toNode();
            System.out.println(jsonNode.toPrettyString());

        }
    }
}
