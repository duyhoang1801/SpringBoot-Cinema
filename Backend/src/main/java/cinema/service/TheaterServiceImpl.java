package cinema.service;

import cinema.dto.FilmDto;
import cinema.dto.TheaterDto;
import cinema.entity.Film;
import cinema.entity.Theater;
import cinema.form.*;
import cinema.repository.FilmRepository;
import cinema.repository.TheaterRepository;
import cinema.specification.FilmSpecification;
import cinema.specification.TheaterSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl implements TheaterService {

    private TheaterRepository theaterRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TheaterServiceImpl(
            TheaterRepository theaterRepository,
            ModelMapper modelMapper
    ) {
        this.theaterRepository = theaterRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<TheaterDto> viewTheater(TheaterFilterForm form, Pageable pageable) {
        Specification<Theater> theaterSpec = TheaterSpecification.buildSpec(form);
        Page<Theater> theaters = theaterRepository.findAll(theaterSpec, pageable);
        Page<TheaterDto> theatersDto = theaters.map(theater -> modelMapper.map(theater, TheaterDto.class));

        return theatersDto;
    }



    @Override
    public TheaterDto createTheater(TheaterCreaterForm form) {

        Theater theater = modelMapper.map(form, Theater.class);
        Theater savedTheater = theaterRepository.save(theater);
        return modelMapper.map(savedTheater, TheaterDto.class);

    }



    @Override
    public TheaterDto updateTheater(Integer id, TheaterUpdateForm form) {
        Theater theater = theaterRepository.findById(id)
                .orElse(null);
        modelMapper.map(form, theater);
        Theater savedTheater = theaterRepository.save(theater);
        return modelMapper.map(savedTheater, TheaterDto.class);
    }




    @Override
    public void deleteTheater(Integer id) {
        theaterRepository.deleteById(id);

    }
}
