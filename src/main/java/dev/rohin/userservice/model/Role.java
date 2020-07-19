package dev.rohin.userservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
                                 inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List <Permission> permissions;

    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "roles") // LAZY will only fetch the object when it is specifically accessed by the user
//    To load it on-demand (i.e. lazily)
    private List <User> users; // to make Bi - directional
}
