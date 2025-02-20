package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class NationalIdentityCard {
    @Id
    @Column(length = 12)
    private String number;
    @Column(length = 45)
    private String name;
    @Column(columnDefinition = "Text")
    private String address;
    @OneToOne(targetEntity = Licence.class,mappedBy = "nic")
    private Licence licence;

    public NationalIdentityCard() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
