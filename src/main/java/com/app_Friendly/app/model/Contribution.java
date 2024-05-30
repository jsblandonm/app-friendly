package com.app_Friendly.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Contributions")
public class Contribution {
    @Id
    private  String id;

    @DBRef
    private People people;

    @DBRef
    private Group group;

    private double amount;
    public Contribution(People people, Group group, double amount) {
        this.people = people;
        this.group = group;
        this.amount = amount;
    }
}
