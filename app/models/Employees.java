package models;

import javax.persistence.*;

import javax.persistence.Id;

@Entity
public class Employees
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    public Long employeeId;

    @Column(name = "firstName")
    public String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column(name = "nickName")
    public String nickName;

    @Column(name = "title")
    public String title;

    @Column(name = "titleOfCourtesy")
    public String titleOfCourtesy;

    @Column(name = "address")
    public String address;

    @Column(name = "city")
    public String city;

    @Column(name = "notes")
    public String notes;

    @Column(name = "photo")
    public byte[] photo;

    @ManyToOne(optional=true)
    @JoinColumn(name = "reportsTo")
    public Employees manager;
}
