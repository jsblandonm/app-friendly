package com.app_Friendly.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Groups")
public class Group {

    @Id
    private String id;
    private String name;
    private double totalAmount;

    @DBRef
    private People owner;

    @DBRef
    private List<People> members = new ArrayList<>();

    public Group(String name, People owner) {
        this.name = name;
        this.owner = owner;
        this.members = new ArrayList<>();

    }
}
