package com.example.hellospringbatch.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expenditure extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private UserCategory category;

    @Column
    private boolean environment;

    @ManyToOne
    private User user;

}
