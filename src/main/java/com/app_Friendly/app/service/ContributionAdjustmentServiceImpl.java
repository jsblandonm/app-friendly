package com.app_Friendly.app.service;

import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionAdjustmentServiceImpl implements ContributionAdjustmentService{

    @Autowired
    private  GroupRepository groupRepository;
    @Autowired
    private ContributionService contributionService;

    public ContributionAdjustmentServiceImpl(GroupRepository groupRepository, ContributionService contributionService) {
        this.groupRepository = groupRepository;
        this.contributionService = contributionService;
    }

    public ContributionAdjustmentServiceImpl() {
        super();
    }


    @Override
    public void adjustContributions(String groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("El grupo no existe"));

        int numMembers = group.getMembers().size() + 1;
        double averageContribution = group.getTotalAmount() / numMembers;

        //Ajustar la contribucion del propietario  el grupo
        People owner = group.getOwner();
        double ownerContribution = owner.getContribution(group);
        double ownerDifference = ownerContribution - averageContribution;
        if (ownerDifference != 0 ){
            Contribution contribution = contributionService.createContribution(owner, group, -ownerDifference);
            System.out.println("Se creó una contribución de " + (-ownerDifference) + " para " + owner.getName() + " en el grupo " + group.getName());
        }

        //Ajusta la contribucion de los miembros
        for (People member : group.getMembers()){
            double currentContribution = member.getContribution(group);
            double difference = currentContribution - averageContribution;
            if (difference != 0 ){
                Contribution contribution = contributionService.createContribution(member, group, -difference);
                System.out.println("Se creó una contribución de " + (-difference) + " para " + member.getName() + " en el grupo " + group.getName());
            }
        }
    }
}
