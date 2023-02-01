package com.example.demo.realestate.entity;


import com.example.demo.type.entity.Type;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="real_estates")
public class RealEstate implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE)
    Long id;
    String name;

    String city;
    int age;

    @ManyToOne
    @JoinColumn(name="types")
    Type type;


}
