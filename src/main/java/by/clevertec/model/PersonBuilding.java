package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonBuilding {
    private String buildingType;
    private long age;
    private Person person;
}
