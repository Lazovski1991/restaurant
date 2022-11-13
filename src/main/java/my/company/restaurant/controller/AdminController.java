package my.company.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantDTO;
import my.company.restaurant.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "add restaurant")
    @PostMapping("/add")
    public void addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        adminService.add(restaurantDTO);
    }

    @Operation(summary = "delete restaurant")
    @DeleteMapping("/delete/{id}")
    public void deleteRestaurant(@PathVariable UUID id) {
        adminService.delete(id);
    }
}
