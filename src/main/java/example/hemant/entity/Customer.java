package example.hemant.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String LastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
