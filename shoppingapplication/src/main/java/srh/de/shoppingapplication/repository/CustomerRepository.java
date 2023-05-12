package srh.de.shoppingapplication.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import srh.de.shoppingapplication.models.Customer;

@Repository
public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    // Custom query methods, if needed
}

