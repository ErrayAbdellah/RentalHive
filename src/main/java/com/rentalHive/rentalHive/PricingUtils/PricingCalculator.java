package com.rentalHive.rentalHive.PricingUtils;

import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Equipment;

import java.util.Date;
import java.util.List;

public class PricingCalculator {


    public static double calculateTotalPrice(Demande demande) {
        Date reservationDate = demande.getDemande_date();
        Date returnDate = demande.getDate_retour();

        long rentalDays = calculateRentalDays(reservationDate, returnDate);

        double charges = rentalDays * calculateCharges(demande.getEquipment());


        return charges;
    }

    private static long calculateRentalDays(Date reservationDate, Date returnDate) {
        // Calculate the difference in milliseconds and convert to days
        long millisecondsPerDay = 24 * 60 * 60 * 1000;
        return (returnDate.getTime() - reservationDate.getTime()) / millisecondsPerDay;
    }

    private static double calculateCharges(List<Equipment> equipmentIds) {
        double charges = 0.0d;

        for (Equipment equipment : equipmentIds) {
            double fixedPart = equipment.getPrice();
            charges += fixedPart;
        }

        return charges;
    }
}
