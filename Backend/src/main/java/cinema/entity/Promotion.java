package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "khuyen_mai")
public class Promotion {
    @Id
    @Column(name = "id_khuyen_mai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "giam_gia")
    private float discount;

//    @Column(name = "trang_thai")
//    private Boolean status;

    @Column(name = "ngay_het_han")
    private LocalDate expiration_date;

    @Column(name = "anh_minh_hoa")
    private String image;

    @Column(name = "noi_dung")
    private String content;

    @OneToMany(mappedBy = "promotion")
    private List<Ticket> tickets;


}
