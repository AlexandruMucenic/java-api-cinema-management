package cinema.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "actors")
@Builder
public class Movie extends BaseEntity<Long> {

    private String title;
    private Integer releaseYear;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @Builder.Default
    private Set<Actor> actors = new HashSet<>();
}
