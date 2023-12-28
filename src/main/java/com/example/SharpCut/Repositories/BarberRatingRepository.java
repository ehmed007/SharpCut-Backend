package com.example.SharpCut.Repositories;

import com.example.SharpCut.Entities.BarberRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRatingRepository extends JpaRepository<BarberRating, Integer> {
}
