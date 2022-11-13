package my.company.restaurant;

import my.company.jwtparselib.service.ParseTokenUtilService;
import my.company.restaurant.exception.RestaurantException;
import my.company.restaurant.model.Vote;
import my.company.restaurant.repository.VoteRepository;
import my.company.restaurant.service.VoteServiceImpl;
import my.company.restaurant.util.TimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private ParseTokenUtilService parseTokenUtilService;
    @Mock
    private TimeService timeService;
    @InjectMocks
    private VoteServiceImpl voteService;

    private static final String USER_ID = "b5b8425e-63a8-11ed-81ce-0242ac120002";
    private static final String RESTAURANT_ID = "e60eb874-63a9-11ed-81ce-0242ac120002";

    @Test
    void addVote() {
        Vote expected = new Vote()
                .setVoteDateTime(LocalDateTime.of(2020, Month.JULY, 24, 22, 0))
                .setRestaurantId(RESTAURANT_ID)
                .setUserId(USER_ID);

        when(parseTokenUtilService.getValueFieldFromToken("token", "userId", false)).thenReturn(USER_ID);
        when(timeService.getLocalDate()).thenReturn(LocalDate.of(2020, Month.JULY, 21));
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.of(2020, Month.JULY, 24, 22, 0));
        when(voteRepository.findAllByUserId(USER_ID)).thenReturn(List.of());

        voteService.addVote("token", UUID.fromString(RESTAURANT_ID));

        verify(voteRepository).save(eq(expected));
    }

    @Test
    void addVote_when_beforeDeadline() {
        String newRestaurant = "e60eb874-63a9-11ed-81ce-0242ac120001";
        String voteId = "3feaf25a-63a9-11ed-81ce-0242ac120002";

        Vote expected = new Vote()
                .setId(UUID.fromString(voteId))
                .setVoteDateTime(LocalDateTime.of(2020, Month.JULY, 24, 10, 0))
                .setRestaurantId(newRestaurant)
                .setUserId(USER_ID);

        when(parseTokenUtilService.getValueFieldFromToken("token", "userId", false)).thenReturn(USER_ID);
        when(timeService.getLocalDate()).thenReturn(LocalDate.of(2020, Month.JULY, 24));
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.of(2020, Month.JULY, 24, 10, 0));
        when(voteRepository.findAllByUserId(USER_ID)).thenReturn(getVotes());

        voteService.addVote("token", UUID.fromString(newRestaurant));

        verify(voteRepository).save(eq(expected));
    }

    @Test
    void addVote_when_afterDeadline() {
        String newRestaurant = "e60eb874-63a9-11ed-81ce-0242ac120001";

        when(parseTokenUtilService.getValueFieldFromToken("token", "userId", false)).thenReturn(USER_ID);
        when(timeService.getLocalDate()).thenReturn(LocalDate.of(2020, Month.JULY, 24));
        when(timeService.getLocalDateTime()).thenReturn(LocalDateTime.of(2020, Month.JULY, 24, 12, 0));
        when(voteRepository.findAllByUserId(USER_ID)).thenReturn(getVotes());

        assertThrows(RestaurantException.class, () -> voteService.addVote("token", UUID.fromString(newRestaurant)));
    }

    private static List<Vote> getVotes() {
        return List.of(new Vote()
                .setId(UUID.fromString("3feaf25a-63a9-11ed-81ce-0242ac120002"))
                .setVoteDateTime(LocalDateTime.of(2020, Month.JULY, 24, 7, 0))
                .setRestaurantId(RESTAURANT_ID)
                .setUserId(USER_ID));
    }
}
