package my.company.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.VoteDto;
import my.company.restaurant.service.VoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @Operation(summary = "add vote")
    @PostMapping("/{idRestaurant}")
    public void addVote(@RequestHeader(value = "Authorization") String token,
                        @PathVariable UUID idRestaurant) {
        voteService.addVote(token, idRestaurant);
    }

    @Operation(summary = "delete vote")
    @DeleteMapping("/{idVote}")
    public void deleteVote(@PathVariable UUID idVote) {
        voteService.deleteVote(idVote);
    }

    @Operation(summary = "list all vote")
    @GetMapping("/list")
    public List<VoteDto> listVote(@RequestHeader(value = "Authorization") String token) {
       return voteService.getListVote(token);
    }

    @Operation(summary = "vote current day")
    @GetMapping
    public VoteDto getVote(@RequestHeader(value = "Authorization") String token) {
        return voteService.getVote(token);
    }
}
