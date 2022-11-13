package my.company.restaurant.service;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantDTO;
import my.company.restaurant.model.Restaurant;
import my.company.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Override
    public List<RestaurantDTO> getList() {
        List<Restaurant> restaurantList = repository.findAll();

        return restaurantList
                .stream()
                .map(rest -> new RestaurantDTO()
                        .setRestaurantId(rest.getId())
                        .setRestaurantName(rest.getRestaurantName())
                        .setDishName(rest.getDishName())
                        .setPrice(rest.getDishPrice()))
                .collect(Collectors.toList());
    }
}
