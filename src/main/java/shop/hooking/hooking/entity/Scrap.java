package shop.hooking.hooking.entity;


import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="scrap")
@Where(clause = "delete_yn = 0")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="card_id")
    private Card card;

    @Column(name = "delete_yn")
    private int deleteYn;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @Builder
    public Scrap(LocalDateTime createdAt, User user, Card card) {
        this.createdAt = createdAt;
        this.user = user;
        this.card= card;
    }
}
