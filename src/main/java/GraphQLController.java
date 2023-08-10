import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class GraphQLController {

    private final GraphQL graphQL;
    private final ObjectMapper objectMapper;

    @Autowired
    public GraphQLController(GraphQL graphQL, ObjectMapper objectMapper) {
        this.graphQL = graphQL;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> query(@RequestBody Map<String, Object> requestBody) {
        String query = (String) requestBody.get("query"); // Extract the query from the JSON body
        String operationName = (String) requestBody.get("operationName"); // Extract the operation name
        Map<String, Object> variables = (Map<String, Object>) requestBody.get("variables"); // Extract the variables

        // Create an ExecutionInput instance with the extracted values
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
            .query(query)
            .operationName(operationName)
            .variables(variables)
            .build();

        // Execute the GraphQL query using the provided GraphQL instance
        ExecutionResult executionResult = graphQL.execute(executionInput);

        // Convert the executionResult to a Map that can be returned in the response
        Map<String, Object> response = executionResult.toSpecification();
        return ResponseEntity.ok(response);
    }
}
