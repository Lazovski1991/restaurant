package my.company.restaurant.repository;

import my.company.restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
//    @Query("select u from Vote u where u.userId=:userId")
    List<Vote> findAllByUserId(String userId);
}
