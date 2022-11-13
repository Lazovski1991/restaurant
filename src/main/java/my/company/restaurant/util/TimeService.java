package my.company.restaurant.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TimeService {
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
