package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractServiceImpl implements IContractService {

    private final IContractRep iContractRep;
    @Autowired
    public ContractServiceImpl(IContractRep iContractRep)
    {
        this.iContractRep = iContractRep;
    }

    @Override
    public List<Contrat> getAllContracts() {
        return null;
    }

    @Override
    public List<Contrat> getContractsByStatus(Status status) {
        return iContractRep.findByStatus(status);
    }
}
