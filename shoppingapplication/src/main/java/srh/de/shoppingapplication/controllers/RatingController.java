package srh.de.shoppingapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import srh.de.shoppingapplication.models.Customer;
import srh.de.shoppingapplication.models.Product;
import srh.de.shoppingapplication.models.Rating;
import srh.de.shoppingapplication.services.RatingService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
@CrossOrigin(origins = "http://localhost:4200")

public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public Iterable<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId) {
        Optional<Rating> rating = ratingService.getRatingById(ratingId);
        return rating.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/ratings", method = RequestMethod.POST)
    public ResponseEntity<Rating> createRating(@RequestParam Long customerId,
                                               @RequestParam Long productId,
                                               @RequestParam int value) {
        // create the rating object with the given parameters
        Rating rating = new Rating();
        rating.setValue(value);
        rating.setCustomer(new Customer(customerId));
        rating.setProduct(new Product(productId));

        // call the createRating method in the RatingService class
        Rating createdRating = ratingService.createRating(rating, customerId, productId);

        // return the response
        return ResponseEntity.created(URI.create("/ratings/" + createdRating.getId())).body(createdRating);
    }



    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, @RequestBody Rating rating) {
        Rating updatedRating = ratingService.updateRating(ratingId, rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build();
    }
}

