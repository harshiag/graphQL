import graphql.kickstart.tools.GraphQLQueryResolver;
//import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class GraphQLResolver implements GraphQLQueryResolver {
    public String hello() {
        return "Hello, GraphQL!";
    }
}
