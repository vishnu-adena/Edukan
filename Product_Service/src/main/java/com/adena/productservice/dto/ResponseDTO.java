package com.adena.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseDTO {
    private long id;
    private Date created_At;
    private Date updated_At;
    private String title;
    private double price;
    private String description ;
    private String category;
    private String image;
}
