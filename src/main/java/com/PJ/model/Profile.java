package com.PJ.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Profile {
    @Id
    private Long id; // same as user id

    private String fullName;
    private String email;
    private String major;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
