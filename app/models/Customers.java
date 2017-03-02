package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customers
{
    @Id
    @Column(name = "customerId")
    public String customerId;

    @Column(name = "companyName")
    public String companyName;

    @Column(name = "contactName")
    public String contactName;


}