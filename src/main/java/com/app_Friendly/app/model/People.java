package com.app_Friendly.app.model;

import com.app_Friendly.app.DTO.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Peoples")
public class People {
    @Id
    private String id;
    private String name;
    private String email;
    private Long bankAccount;
    private String password;
    private String imageUrl;

    private Map<String, Double> contributionByGroup = new HashMap<>();

    public People(String name, String email, Long bankAccount, String password) {
        this.name = name;
        this.email = email;
        this.bankAccount = bankAccount;
        this.password = password;
    }

    public People(String name, String email, String hashedPassword) {
    }


    public boolean authenticate(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }

    public void addContribution(Group group, double amount) {
        contributionByGroup.put(group.getId(), contributionByGroup.getOrDefault(group.getId(), 0.0) + amount);
    }


    public double getContribution(Group group) {
        return contributionByGroup.getOrDefault(group.getId(), 0.0);
    }


    public Map<String, Double> getGroupContributions(){
        return contributionByGroup;
    }
}
