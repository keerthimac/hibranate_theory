package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentDTO {
    private int id;
    private String name;
    private String age;
    private String address;
}
