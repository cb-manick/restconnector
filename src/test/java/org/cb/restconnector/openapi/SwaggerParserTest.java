package org.cb.restconnector.openapi;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.exception.ResolutionException;
import org.openapi4j.core.validation.ValidationException;

import java.net.MalformedURLException;

public class SwaggerParserTest {
    private SwaggerParseResult result = null;
    @BeforeClass
    public void testSetup() throws MalformedURLException, ResolutionException, ValidationException {
       result = new OpenAPIParser().readLocation("https://petstore3.swagger.io/api/v3/openapi.json", null, null);

    }

    @Test
    public void testSwaggerParser()
            throws MalformedURLException, ResolutionException, ValidationException, EncodeException {
        }
    }

}
