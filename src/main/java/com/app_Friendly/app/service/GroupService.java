package com.app_Friendly.app.service;

import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.ContributionRepository;
import com.app_Friendly.app.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private ContributionService contributionService;

    public Group createGroup(String name, People owner){
        // Validar el nombre del grupo
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("El nombre del grupo está vacío");
        }

        // Verificar si el grupo ya existe
        if (groupRepository.findByName(name) != null){
            throw new IllegalArgumentException("El grupo ya existe");
        }

        Group group = new Group(name, owner);
        return groupRepository.save(group);
    }

    public Group addMember(String groupId, People people){
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("El Grupo no existe"));
        if (group.getMembers().contains(people)){
            throw new IllegalArgumentException("La persona ya es miembro del grupo");
        }
        group.addMember(people);
        return groupRepository.save(group);
    }

    public Group updateGroup(String id, String name) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));

        if (name != null && !name.isEmpty()) {
            // Verificar si el nuevo nombre ya está en uso por otro grupo
            if (!name.equals(group.getName()) && groupRepository.findByName(name) != null) {
                throw new IllegalArgumentException("El nombre del grupo ya está en uso");
            }
            group.setName(name);
        }

        return groupRepository.save(group);
    }

    public void removeMember(String groupId, People people) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));

        if (!group.getMembers().contains(people)) {
            throw new IllegalArgumentException("La persona no es miembro del grupo");
        }

        group.getMembers().remove(people);
        groupRepository.save(group);
    }

    // Logica para ajustar las contribuciones
    public void adjustContributions(String groupId){
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));

        double average = group.calculateAverageContribution();
        for (People member : group.getMembers()){
            double currentContribution = member.getContribution(group);
            double difference = currentContribution - average;
            if (difference != 0 ){
                Contribution contribution = contributionService.createContribution(member, group, -difference);
                System.out.println("Se creó una contribución de " + (-difference) + " para " + member.getName() + " en el grupo " + group.getName());
            }
        }
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public Group findById(String id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));
    }
}
