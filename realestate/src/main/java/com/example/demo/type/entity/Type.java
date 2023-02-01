package com.example.demo.type.entity;

import com.example.demo.realestate.entity.RealEstate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "types")
public class Type {
    @Id
    String name;

    @OneToMany(mappedBy = "type",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    List<RealEstate> realEstates;
}
