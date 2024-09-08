package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryCarBill {
    private String country;
    private double totalPrice;
    private double totalTax;
    private double totalMass;
}
