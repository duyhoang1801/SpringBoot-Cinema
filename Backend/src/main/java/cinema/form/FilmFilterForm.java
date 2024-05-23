package cinema.form;

import cinema.entity.Country;
import cinema.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
//Class FilmFilterForm dùng để nhập hứng thông tin filter Film
public class FilmFilterForm {

    //Tim kiếm theo tên phim
    private String name;


    //Tìm kiếm theo quốc gia (nhập vào tên quốc gia ở dạng String)
    private String country;


    //Tìm kiếm theo thể loại (nhập vào tên thể loại ở dạng String)
    private String genre;
}
