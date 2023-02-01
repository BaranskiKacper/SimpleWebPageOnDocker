package com.example.demo.type.entity;

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
@Table(name="types")
public class Type implements Serializable{
    @Id
    String name;
    int size;
    int numberOfRooms;
    Boolean internet_access;



}
