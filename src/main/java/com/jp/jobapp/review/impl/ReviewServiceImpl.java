package com.jp.jobapp.review.impl;

import com.jp.jobapp.company.Company;
import com.jp.jobapp.company.CompanyService;
import com.jp.jobapp.review.Review;
import com.jp.jobapp.review.ReviewRepository;
import com.jp.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.findCompanyById(companyId) != null){
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if(reviewOptional.isPresent()){
                updatedReview.setCompany(companyService.findCompanyById(companyId));
                updatedReview.setId(reviewId);
                reviewRepository.save(updatedReview);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            if (companyService.findCompanyById(companyId) != null && reviewRepository.findById(reviewId).isPresent()) {
                Review review = reviewRepository.findById(reviewId).get();
                Company company = companyService.findCompanyById(companyId);
                company.getReviews().remove(review);
                review.setCompany(null);
                companyService.updateCompany(company, companyId);
                reviewRepository.deleteById(reviewId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
