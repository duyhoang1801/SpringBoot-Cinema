package cinema.repository;

import cinema.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TheaterRepository extends
        JpaRepository<Theater, Integer>,
        JpaSpecificationExecutor<Theater> {
}
