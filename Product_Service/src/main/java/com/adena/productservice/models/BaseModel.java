package com.adena.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_At = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updated_At = new Date();
    @Column(name = "Is_Deleted")
    private boolean isDeleted;
}
