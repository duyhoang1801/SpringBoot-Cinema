package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@Table(name = "danh_gia")
public class Review {
    @Id
    @Column(name = "id_danh_gia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "noi_dung", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Account account;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_film")
    private Film film;

}
