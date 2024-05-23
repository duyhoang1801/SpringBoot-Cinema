package cinema.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmUpdateForm {

    private String name;

    private String description;

    private LocalDate releaseDate;

    private String actor;

    private String director;

    private Integer runtime;

    private Integer age;

    private String image;

//    //Người dùng nhập vào countryName dạng String
//    private String countryName;
//
//    //Người dùng nhập vào genreName dạng String
//    private String genreName;

}
