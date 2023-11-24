package com.rentalHive.rentalHive.service.implementations;
import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentalRecordServiceImplTest {
    @Mock
    private IRentalRecordRepo rentalRecordRepo;

    @Mock
    private IUserRepo userRepo;

    @Mock
    private IEquipmentRepo iEquipmentRepo;

    @InjectMocks
    private RentalRecordServiceImpl rentalRecordService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testRecordSuccess() throws ParseException {
        User user = new User();
        user.setUserId(1L);

        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1L);

        RentalRecordDTO rentalRecordDTO = new RentalRecordDTO();
        rentalRecordDTO.setUser(user);
        rentalRecordDTO.setEquipment(equipment);
        rentalRecordDTO.setReservationDate(dateFormat.parse("2023-01-04"));
        rentalRecordDTO.setReturnDate(dateFormat.parse("2023-02-04"));

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(iEquipmentRepo.findById(1L)).thenReturn(Optional.of(equipment));
        when(rentalRecordRepo.save(any(RentalRecord.class))).thenReturn(new RentalRecord());

        ResponseEntity response = rentalRecordService.record(rentalRecordDTO);

        assertEquals(ResponseEntity.ok("record is successfully"), response);

        verify(userRepo, times(1)).findById(1L);
        verify(iEquipmentRepo, times(1)).findById(1L);
        verify(rentalRecordRepo, times(1)).save(any(RentalRecord.class));
    }
}
