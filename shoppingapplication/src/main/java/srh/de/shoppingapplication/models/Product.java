package srh.de.shoppingapplication.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Product(Long productId) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }
}

