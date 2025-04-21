package cinema.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actors", uniqueConstraints = {
                @UniqueConstraint(name = "uk_actor_ssn", columnNames = "ssn")
})

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Actor extends BaseEntity<Long> {
        @Column(unique = true)
        private String ssn;
        private String name;
        private Integer age;
        private String nationality;
        private Integer awardsCount;

}
