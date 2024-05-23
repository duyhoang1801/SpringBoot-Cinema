package cinema.repository;

import cinema.entity.Account;
import cinema.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CountryRespository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country> {
    Country findByName(String countryName);
}
