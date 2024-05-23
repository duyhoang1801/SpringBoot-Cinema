package cinema.service;

import cinema.entity.Account;
import cinema.entity.Seat;
import cinema.form.SeatSearchForm;
import cinema.repository.SeatRopository;
import cinema.specification.AccountSpecification;
import cinema.specification.SeatSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeatService {
    @Autowired
    private SeatRopository seatRopository;
    public List<Seat> getSeatByRow(SeatSearchForm request) {
        Specification<Seat> condition = SeatSpecification.buildCondition(request);
        return seatRopository.findAll(condition);
    }
}
