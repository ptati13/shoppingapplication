/*
package srh.de.shoppingapplication.neo4jconfig;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.internal.SessionFactory;
import org.neo4j.driver.internal.SessionFactoryImpl;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.demo.neo4j.repository")
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public SessionFactoryImpl sessionFactory() {
        return new SessionFactory(configuration(), "");
    }

    @Bean
    public Configuration configuration() {
        Configuration configuration = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .credentials("neo4j", "password")
                .autoIndex("none")
                .build();
        return configuration;
    }

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(neo4jDriver());
    }
}
*/
