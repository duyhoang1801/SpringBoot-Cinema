package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "film")
public class Film {

    @Id
    @Column(name = "id_film")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_phim")
    private String name;

    @Column(name = "noi_dung")
    private String description;

    @Column(name = "khoi_chieu")
    private LocalDate releaseDate;

    @Column(name = "dien_vien")
    private String actor;

    @Column(name = "dao_dien")
    private String director;

    @Column(name = "thoi_luong")
    private Integer runtime;

    @Column(name = "gioi_han_do_tuoi")
    private Integer age;

    @Column(name = "anh_minh_hoa")
    private String image;


    @ManyToOne
    @JoinColumn(name = "id_quoc_gia")
    private Country country;


    @ManyToOne
    @JoinColumn(name = "id_the_loai")
    private Genre genre;

    @OneToMany(mappedBy = "film", orphanRemoval = true)
    private List<Review> review;

    @OneToMany(mappedBy = "film", orphanRemoval = true)
    private List<Movie_showtime> movieShowtime;

}
