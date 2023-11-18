package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IEquipmentService {
    Optional<EquipmentDTO> findEquipmentByName(String name);
}
