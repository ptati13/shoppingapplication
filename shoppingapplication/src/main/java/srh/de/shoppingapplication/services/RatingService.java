package srh.de.shoppingapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import srh.de.shoppingapplication.models.Customer;
import srh.de.shoppingapplication.models.Product;
import srh.de.shoppingapplication.models.Rating;
import srh.de.shoppingapplication.repository.RatingRepository;
import srh.de.shoppingapplication.repository.CustomerRepository;
import srh.de.shoppingapplication.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAllWithCustomerAndProduct();
    }

    public Optional<Rating> getRatingById(Long ratingId) {
        return ratingRepository.findById(ratingId);
    }

    public Rating createRating(Rating rating, Long customerId, Long productId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);

        if (customer.isPresent() && product.isPresent()) {
            rating.setCustomer(customer.get());
            rating.setProduct(product.get());
            return ratingRepository.save(rating);
        } else {
            throw new IllegalArgumentException("Customer or Product not found with provided IDs.");
        }
    }

    public Rating updateRating(Long ratingId, Rating rating) {
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);
        if (existingRating.isPresent()) {
            Rating updatedRating = existingRating.get();
            updatedRating.setValue(rating.getValue());
            // Update other properties as needed
            return ratingRepository.save(updatedRating);
        } else {
            throw new IllegalArgumentException("Rating not found with ID: " + ratingId);
        }
    }

    public void deleteRating(Long ratingId) {
        if (ratingRepository.existsById(ratingId)) {
            ratingRepository.deleteById(ratingId);
        } else {
            throw new IllegalArgumentException("Rating not found with ID: " + ratingId);
        }
    }
}
