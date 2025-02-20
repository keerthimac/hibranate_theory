package entity;

import javax.persistence.*;

@Entity
public class Licence {
    @Id
    @Column(length = 7)
    private String number;
    @Column(length = 4)
    private String bloodGroup;
    @OneToOne(optional = false)
    @JoinColumn(name = "nic_number")
    private NationalIdentityCard nic;

    public Licence(String number, String bloodGroup, NationalIdentityCard nic) {
        this.number = number;
        this.bloodGroup = bloodGroup;
        this.setNic(nic);
    }

    public Licence() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public NationalIdentityCard getNic() {
        return nic;
    }

    public void setNic(NationalIdentityCard nic) {
        this.nic = nic;
    }
}
