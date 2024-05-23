package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "ve")
public class Ticket {
    @Id
    @Column(name = "id_ve")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gia")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai")
    private Promotion promotion;

    @OneToMany(mappedBy = "ticket", orphanRemoval = true)
    private List<FoodTicket> foodTickets;

    @OneToMany(mappedBy = "ticket", orphanRemoval = true)
    private List<ReserveSeat> reserveSeat;



}
