package my.company.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantDTO;
import my.company.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Operation(summary = "Get list restaurant")
    @GetMapping("/list")
    public List<RestaurantDTO> listMenu() {
        return restaurantService.getList();
    }
}
