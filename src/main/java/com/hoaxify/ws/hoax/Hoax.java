package com.hoaxify.ws.hoax;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Hoax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
