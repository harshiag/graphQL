import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

public class GraphQLResolver {
    public static void main(String[] args) {
        GraphQLObjectType queryType = GraphQLObjectType.newObject()
            .name("Query")
            .field(GraphQLFieldDefinition.newFieldDefinition()
                .name("hello")
                .type(graphql.Scalars.GraphQLString)
                .dataFetcher(new DataFetcher<String>() {
                    @Override
                    public String get(DataFetchingEnvironment environment) {
                        return "Hello, GraphQL!";
                    }
                }))
            .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
            .query(queryType)
            .build();
    }
}
