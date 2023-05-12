package srh.de.shoppingapplication.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import srh.de.shoppingapplication.models.Review;

@Repository
public interface ReviewRepository extends Neo4jRepository<Review, Long> {
    // Custom query methods, if needed
}

