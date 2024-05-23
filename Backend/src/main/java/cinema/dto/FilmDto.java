package cinema.dto;

import cinema.entity.Country;
import cinema.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FilmDto {

    private Integer id;

    private String name;

    private String description;

    private LocalDate releaseDate;

    private String actor;

    private String director;

    private Integer runtime;

    private Integer age;

    private String image;

    private String countryName;

    private String genreName;


}
