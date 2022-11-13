package my.company.restaurant.service;

import my.company.restaurant.dto.RestaurantDTO;

import java.util.UUID;

public interface AdminService {
    void add(RestaurantDTO restaurantDTO);

    void delete(UUID id);
}
