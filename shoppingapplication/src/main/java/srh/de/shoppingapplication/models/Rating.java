package srh.de.shoppingapplication.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    private int value;

    // Relationships
    @Relationship(type = "RATED_BY")
    private Customer customer;

    @Relationship(type = "RATED_PRODUCT")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public Rating(Long id, int value, Customer customer, Product product) {
        this.id = id;
        this.value = value;
        this.customer = customer;
        this.product = product;
    }

    public Rating() {
    }
}

