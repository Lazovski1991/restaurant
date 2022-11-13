package my.company.restaurant.service;

import my.company.restaurant.dto.VoteDto;

import java.util.List;
import java.util.UUID;

public interface VoteService {
    void addVote(String token, UUID idRestaurant);

    List<VoteDto> getListVote(String token);

    VoteDto getVote(String token);

    void deleteVote(UUID idVote);
}
