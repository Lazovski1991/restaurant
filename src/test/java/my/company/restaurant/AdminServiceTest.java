package my.company.restaurant;

import my.company.restaurant.dto.RestaurantDTO;
import my.company.restaurant.model.Restaurant;
import my.company.restaurant.repository.AdminRepository;
import my.company.restaurant.service.AdminServiceImpl;
import my.company.restaurant.util.TimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private TimeService timeService;
    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void addRestaurant() {
        LocalDate now = LocalDate.of(2020, Month.JULY, 24);
        String restaurantName = "restaurantName";
        String dishName = "DishName";

        Restaurant expected = new Restaurant()
                .setRestaurantName(restaurantName)
                .setDishName(dishName)
                .setDishDate(now)
                .setDishPrice(BigDecimal.ZERO);

        RestaurantDTO restaurantDTO = new RestaurantDTO()
                .setPrice(BigDecimal.ZERO)
                .setRestaurantName(restaurantName)
                .setDishName(dishName);


        when(adminRepository.findByRestaurantNameAndAndDishDate(restaurantName, now)).thenReturn(Optional.empty());
        when(timeService.getLocalDate()).thenReturn(now);

        adminService.add(restaurantDTO);

        verify(adminRepository).save(expected);
    }

    @Test
    void addRestaurant_when_already_add() {
        LocalDate now = LocalDate.of(2020, Month.JULY, 24);
        String restaurantName = "restaurantName";
        String dishName = "newDishName";
        UUID id = UUID.fromString("43725c1a-63ae-11ed-81ce-0242ac120002");

        Restaurant expected = new Restaurant()
                .setId(id)
                .setRestaurantName(restaurantName)
                .setDishName(dishName)
                .setDishDate(now)
                .setDishPrice(BigDecimal.ZERO);

        RestaurantDTO restaurantDTO = new RestaurantDTO()
                .setPrice(BigDecimal.ZERO)
                .setRestaurantName(restaurantName)
                .setDishName(dishName);

        Restaurant saved = new Restaurant()
                .setId(id)
                .setRestaurantName(restaurantName)
                .setDishName("oldDishName")
                .setDishDate(now)
                .setDishPrice(BigDecimal.ZERO);

        when(adminRepository.findByRestaurantNameAndAndDishDate(restaurantName, now)).thenReturn(Optional.of(saved));
        when(timeService.getLocalDate()).thenReturn(now);

        adminService.add(restaurantDTO);

        verify(adminRepository).save(expected);
    }
}
