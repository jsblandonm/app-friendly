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

    @DBRef
    private List<People> members = new ArrayList<>();

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(People people){
        members.add(people);
    }

    public double calculateTotalContributions(){
        return members.stream()
                .mapToDouble(member -> member.getContribution(this))
                .sum();
    }

    public double calculateAverageContribution(){
        return calculateTotalContributions() / members.size();
    }

    public void adjustContributions(){
        double average = calculateAverageContribution();
        for (People member : members){
            double currentContribution = member.getContribution(this);
            double diference = currentContribution - average;
        }
    }
}
