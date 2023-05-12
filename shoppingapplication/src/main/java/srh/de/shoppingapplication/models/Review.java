package srh.de.shoppingapplication.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    // Relationships
    @Relationship(type = "REVIEWED_BY")
    private Customer customer;

    @Relationship(type = "REVIEWED_PRODUCT")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Review(Long id, String content, Customer customer, Product product) {
        this.id = id;
        this.content = content;
        this.customer = customer;
        this.product = product;
    }

    public Review() {
    }
}

