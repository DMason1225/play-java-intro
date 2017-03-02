package models;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
public class Orders
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "orderId")
    public Long orderId;

    @ManyToOne(optional=false)
    @JoinColumn(name = "employeeId")
    public Employees employee;
}