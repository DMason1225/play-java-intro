package models;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class  Categories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    public Long categoryId;

    @Column(name = "categoryName")
    public String categoryName;

    @Column(name = "description")
    public String description;

    @Column(name = "picture")
    public byte[] picture;
}
