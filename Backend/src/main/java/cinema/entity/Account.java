package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name ="id_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "ho_ten", length = 50, nullable = false)
    private String fullName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "mat_khau", nullable = false)
    private String password;

    @Column(name = "gioi_tinh")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "ngay_sinh")
    private LocalDate dateOfBirth;

    @Column(name = "so_dien_thoai")
    private String phoneNumber;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Review> reviews;
}
