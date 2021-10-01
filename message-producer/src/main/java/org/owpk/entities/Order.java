package org.owpk.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.owpk.utils.CartManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonAutoDetect
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    public Order(User user, CartManager cart) {
        this.user = user;
        items = new ArrayList<>();
        cart.getItemList().forEach(x -> {
            x.setOrder(this);
            items.add(x);
        });
    }
}
