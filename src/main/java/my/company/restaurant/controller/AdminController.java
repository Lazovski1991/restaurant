package my.company.restaurant.controller;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantRequestDTO;
import my.company.restaurant.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add")
    public void addRestaurant(@RequestBody RestaurantRequestDTO restaurantDTO) {

    }

    @DeleteMapping("/delete/{id}")
    public void deleteRestaurant(@PathVariable UUID id) {

    }

    @PutMapping("/update")
    public void updateRestaurant(@RequestBody RestaurantRequestDTO restaurantDTO) {

    }
}
