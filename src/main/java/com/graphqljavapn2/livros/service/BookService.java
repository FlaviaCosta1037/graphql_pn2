package com.graphqljavapn2.livros.service;

import java.io.IOException;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class BookService {
    
    @Value("classpath:graphql/schema.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private FetchFindAllBook fetchFindAllBook;


    @Autowired
    private FetchFindByIdBook fetchFindByIdBook;

    @Autowired
    private MutationBook mutationBook;

    @Autowired
    private MutationAddBook mutationAddBook;

    @PostConstruct
    private void getSchema() throws IOException {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(resource.getFile());
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findAllBook", fetchFindAllBook)
                        .dataFetcher("findByIdBook", fetchFindByIdBook))
                .type("Mutation", typeWiring-> typeWiring
                        .dataFetcher("addBook", mutationAddBook)
                        .dataFetcher("deleteBook", mutationBook))        
                .build();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
}
