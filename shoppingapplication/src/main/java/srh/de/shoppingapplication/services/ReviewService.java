package srh.de.shoppingapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import srh.de.shoppingapplication.models.Review;
import srh.de.shoppingapplication.repository.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long reviewId, Review review) {
        Optional<Review> existingReview = reviewRepository.findById(reviewId);
        if (existingReview.isPresent()) {
            Review updatedReview = existingReview.get();
            updatedReview.setContent(review.getContent());
            // Update other properties as needed
            return reviewRepository.save(updatedReview);
        } else {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
    }

    public void deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
    }
}

