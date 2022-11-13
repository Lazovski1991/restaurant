package my.company.restaurant.service;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantDTO;
import my.company.restaurant.model.Restaurant;
import my.company.restaurant.repository.AdminRepository;
import my.company.restaurant.util.TimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final TimeService timeService;

    @Transactional
    @Override
    public void add(RestaurantDTO restaurantDTO) {
        Optional<Restaurant> optRestaurantName =
                adminRepository.findByRestaurantNameAndAndDishDate(restaurantDTO.getRestaurantName(), timeService.getLocalDate());

        Restaurant restaurant = new Restaurant()
                .setDishName(restaurantDTO.getDishName())
                .setRestaurantName(restaurantDTO.getRestaurantName())
                .setDishDate(timeService.getLocalDate())
                .setDishPrice(restaurantDTO.getPrice());

        if (optRestaurantName.isPresent()) {
            Restaurant restaurantDB = optRestaurantName.get();

            restaurant
                    .setId(restaurantDB.getId());
        }

        adminRepository.save(restaurant);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        adminRepository.deleteById(id);
    }
}
