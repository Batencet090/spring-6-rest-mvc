package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String customerName;
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(length = 36 , columnDefinition = "varchar" , updatable = false , nullable = false)
    private UUID id;
    @Version
    private int version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}