package cinema.form;

import cinema.entity.Country;
import cinema.entity.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class FilmCreateForm {

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

//    //Người dùng nhập vào countryName dạng String
//    private String countryName;
//
//    //Người dùng nhập vào genreName dạng String
//    private String genreName;

}
