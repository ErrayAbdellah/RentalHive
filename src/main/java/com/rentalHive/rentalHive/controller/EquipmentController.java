package com.rentalHive.rentalHive.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

//    @Autowired
//    private EquipmentRepo equipmentRepo;
//    private final EquipmentServiceImpl equipmentService;
//    @Autowired
//    public EquipmentController(EquipmentRepo equipmentRepo, EquipmentServiceImpl equipmentService) {
//        this.equipmentRepo = equipmentRepo;
//        this.equipmentService = equipmentService;
//    }
//    @PostMapping
//    public ResponseEntity<String> createEquipement(@Valid @RequestBody EquipmentDTO equipmentDTO)
//    {
//        Equipment newEquipment = new Equipment();
//        BeanUtils.copyProperties(equipmentDTO,newEquipment);
//        equipmentRepo.save(newEquipment);
//        equipmentRepo.save(newEquipment);
//        equipmentRepo.save(newEquipment);
//        return  ResponseEntity.ok("Equipement created succesfully");
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<Equipment>> getAllEquipment() {
//        List<Equipment> equipment = equipmentRepo.findAll();
//        return ResponseEntity.ok(equipment);
//    }
//
//    @PutMapping("/{equipmentId}")
//    public ResponseEntity<String> updateEquipment(
//            @PathVariable Long equipmentId,
//            @RequestBody EquipmentDTO equipmentDTO) {
//
//        Optional<Equipment> optionalEquipment = equipmentRepo.findById(equipmentId);
//
//        if (optionalEquipment.isPresent()) {
//            Equipment existingEquipment = optionalEquipment.get();
//
//            existingEquipment.setName(equipmentDTO.getName());
//            existingEquipment.setPrice(equipmentDTO.getPrice());
//            existingEquipment.setQuantity(equipmentDTO.getQuantity());
//
//            equipmentRepo.save(existingEquipment);
//
//            return ResponseEntity.ok("Equipment updated successfully.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    @GetMapping("/findByName/{name}")
//    public ResponseEntity<EquipmentDTO> findEquipmentByName(@PathVariable String name) {
//        Optional<EquipmentDTO> equipmentDTOOptional = equipmentService.findEquipmentByName(name);
//
//        return equipmentDTOOptional
//                .map(equipmentDTO -> new ResponseEntity<>(equipmentDTO, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping("/findByStatus/{status}")
//    public ResponseEntity<List<EquipmentDTO>> findEquipmentByStatus(@PathVariable Status status) {
//        List<EquipmentDTO> equipmentList = equipmentService.findEquipmentByStatus(status);
//
//        if (!equipmentList.isEmpty()) {
//            return new ResponseEntity<>(equipmentList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}

