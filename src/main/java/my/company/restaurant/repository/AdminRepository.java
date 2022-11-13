package my.company.restaurant.repository;

import my.company.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Restaurant, UUID> {
    Optional<Restaurant> findByRestaurantNameAndAndDishDate(String restaurantName, LocalDate dishDate);
}
