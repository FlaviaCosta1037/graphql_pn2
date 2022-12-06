package com.graphqljavapn2.livros.service;

import java.io.IOException;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class AuthorService {
    
    @Value("classpath:graphql/schema.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private FetchFindAllAuthor fetchFindAllAuthor;


    @Autowired
    private FetchFindByIdAuthor fetchFindByIdAuthor;

    @Autowired
    private MutationAuthor mutationAuthor;

    @Autowired
    private MutationAddAuthor mutationAddAuthor;

    @PostConstruct
    private void getSchema() throws IOException {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(resource.getFile());
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findAllAuthor", fetchFindAllAuthor)
                        .dataFetcher("findByIdAuthor", fetchFindByIdAuthor))
                .type("Mutation", typeWiring-> typeWiring
                        .dataFetcher("addAuthor", mutationAddAuthor)
                        .dataFetcher("deleteAuthor", mutationAuthor))        
                .build();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
}
