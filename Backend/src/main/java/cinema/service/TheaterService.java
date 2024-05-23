package cinema.service;

import cinema.dto.FilmDto;
import cinema.dto.TheaterDto;
import cinema.entity.Film;
import cinema.entity.Theater;
import cinema.form.*;
import cinema.repository.FilmRepository;
import cinema.specification.FilmSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface TheaterService {

    public Page<TheaterDto> viewTheater(TheaterFilterForm form, Pageable pageable);


    public TheaterDto createTheater(TheaterCreaterForm form);


    public TheaterDto updateTheater(Integer id, TheaterUpdateForm form);


    public void deleteTheater(Integer id);

}
