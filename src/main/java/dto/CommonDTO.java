package dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonDTO {
    private int id;
    private String name;
    private String address;
    private String bloodGroup;
    private String nicNumber;
    private String licenceNumber;
}
