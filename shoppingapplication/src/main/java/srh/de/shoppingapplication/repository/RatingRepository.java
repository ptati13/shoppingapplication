package srh.de.shoppingapplication.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import srh.de.shoppingapplication.models.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends Neo4jRepository<Rating, Long> {
    @Query
            ("MATCH (r:Rating)-[:RATED_BY]->(c:Customer), (r)-[:RATED_PRODUCT]->(p:Product) RETURN r, c, p")
    List<Rating> findAllWithCustomerAndProduct();
// Custom query methods, if needed
}

