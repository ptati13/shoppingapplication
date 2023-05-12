package srh.de.shoppingapplication.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import srh.de.shoppingapplication.models.Product;

@Repository
public interface ProductRepository extends Neo4jRepository<Product, Long> {
    // Custom query methods, if needed
}

