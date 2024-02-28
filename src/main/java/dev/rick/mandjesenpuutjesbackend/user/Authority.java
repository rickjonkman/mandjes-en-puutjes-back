package dev.rick.mandjesenpuutjesbackend.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(Authority.class)
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    private String username;

    @Id
    private String authority;
}
