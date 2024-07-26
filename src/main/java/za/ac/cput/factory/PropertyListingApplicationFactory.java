package za.ac.cput.factory;


import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

/*
 * Author : Sabelo Kama (219149364)
 * Date : 22 April 2024
 * */
public class PropertyListingApplicationFactory {
    public static PropertyListingApplication propertyApp(LocalDate date,
                                                         String status, Property property,
                                                         Landlord landlord, double rentalPrice,
                                                         String location, Address address){
        if (Helper.isInvalidDate(date) ||
            Helper.isNullOrEmpty(status) ||
            Helper.isObjectNull(property) ||
            Helper.isObjectNull(landlord) ||
            Helper.invalidDouble(rentalPrice) ||
            Helper.isNullOrEmpty(location) ||
            Helper.isObjectNull(address))
            return null;

        return new PropertyListingApplication.Builder()
                .setDate(date)
                .setStatus(Application.Status.valueOf(status))
                .setProperty(property)
                .setLandlord(landlord)
                .setRentalPrice(rentalPrice)
                .setLocation(location)
                .setAddress(address)
                .build();
    }
}
