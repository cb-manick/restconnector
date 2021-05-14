package org.cb.restconnector.openapi;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.exception.ResolutionException;
import org.openapi4j.core.model.OAIContext;
import org.openapi4j.core.validation.ValidationException;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.model.v3.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class OpenApiParserTest {

  private final OpenApi3 api = null;
  @BeforeClass
  public void testSetup() throws MalformedURLException, ResolutionException, ValidationException {
    api =   new OpenApi3Parser()
            .parse(new URL("https://petstore3.swagger.io/api/v3/openapi.json"), false);
  }

  @Test
  public void testOpenApiParser()
      throws MalformedURLException, ResolutionException, ValidationException, EncodeException {

//    OpenApi3 api =
//        new OpenApi3Parser()
//            .parse(new URL("https://petstore3.swagger.io/api/v3/openapi.json"), false);
//   // "https://petstore3.swagger.io/api/v3/openapi.json", null, null);

    Components components = api.getComponents(); // components

    Map<String, Path> paths = api.getPaths();

    for (String path : paths.keySet()) {

      Path pathObj = paths.get(path);
      Map<String, Operation> operations = pathObj.getOperations();
      for (String operation : operations.keySet()) {
        Operation operation1 = operations.get(operation);
        System.out.println(operation1.getOperationId());
        List<Parameter> parameters = operation1.getParameters();
        if (parameters != null) {
            parameters.forEach(x->System.out.println(x.getName()));
        //  System.out.println(parameters.toString());
        }
        RequestBody requestBody = operation1.getRequestBody();

        if (requestBody != null) {
          MediaType contentMediaType = requestBody.getContentMediaType("application/json");
          if (contentMediaType != null) {
            String ref = contentMediaType.getSchema().getRef();
            if (ref != null) {
              String[] split = ref.split("/");
              Schema schema = components.getSchema(split[split.length - 1]);
              if (schema != null) {
                System.out.println(schema.toNode().toPrettyString());
              }
            }
          }
        }

        //  requestBody.toNode();
      }
      System.out.println("---");
      //      JsonNode jsonNode = pathObj.toNode();
      //      System.out.println(jsonNode.toPrettyString());
      //      System.out.println("---");
    }
    }


  @Test
  public void getSecurityRequirements() {

    OAIContext context = api.getContext();
    List<SecurityRequirement> securityRequirements = api.getSecurityRequirements();



    for (String path : api.) {
      System.out.println(path.get);
    }
    }

  }

    @Test
    public void getMethodDetails() {

    }


}
