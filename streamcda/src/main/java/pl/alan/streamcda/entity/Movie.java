package pl.alan.streamcda.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    private String genre;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String thumbnailUrl;
    @Column(nullable = false)
    private boolean isPremium;
}
