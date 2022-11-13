package my.company.restaurant.service;

import lombok.RequiredArgsConstructor;
import my.company.jwtparselib.service.ParseTokenUtilService;
import my.company.restaurant.dto.VoteDto;
import my.company.restaurant.exception.RestaurantException;
import my.company.restaurant.model.Vote;
import my.company.restaurant.repository.VoteRepository;
import my.company.restaurant.util.TimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final ParseTokenUtilService parseTokenUtilService;
    private final VoteRepository voteRepository;
    private final TimeService timeService;

    @Override
    @Transactional
    public void addVote(String token, UUID idRestaurant) {
        String userId = parseTokenUtilService.getValueFieldFromToken(token, "userId", false);

        LocalDateTime deadLineTime = timeService.getLocalDate().atTime(LocalTime.of(11, 0));
        VoteDto currentVote = getVote(token);

        Vote newVote;

        if (currentVote == null) {
            newVote = new Vote()
                    .setVoteDateTime(timeService.getLocalDateTime())
                    .setUserId(userId)
                    .setRestaurantId(idRestaurant.toString());
        } else if (timeService.getLocalDateTime().isBefore(deadLineTime)) {
            newVote = new Vote()
                    .setId(currentVote.getId())
                    .setVoteDateTime(timeService.getLocalDateTime())
                    .setUserId(userId)
                    .setRestaurantId(idRestaurant.toString());
        } else {
            throw new RestaurantException("You don't need change vote");
        }

        voteRepository.save(newVote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoteDto> getListVote(String token) {
        String userId = parseTokenUtilService.getValueFieldFromToken(token, "userId", false);

        return voteRepository.findAllByUserId(userId)
                .stream()
                .map((vote) -> new VoteDto()
                        .setId(vote.getId())
                        .setUserId(vote.getUserId())
                        .setRestaurantId(vote.getRestaurantId())
                        .setVoteDateTime(vote.getVoteDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VoteDto getVote(String token) {
        return getListVote(token)
                .stream()
                .filter((vote) -> vote.getVoteDateTime().toLocalDate().equals(timeService.getLocalDate()))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteVote(UUID idVote) {
        voteRepository.deleteById(idVote);
    }
}
