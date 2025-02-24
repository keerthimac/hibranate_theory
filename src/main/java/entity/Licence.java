package entity;

import javax.persistence.*;

@Entity
public class Licence {
    @Id
    @Column(length = 7)
    private String lNumber;
    @Column(length = 4)
    private String bloodGroup;
    @OneToOne(optional = false , cascade = CascadeType.ALL)
    @JoinColumn(name = "nic_number")
    private NicEntity nic;

    public Licence(String number, String bloodGroup, NicEntity nic) {
        this.setlNumber(number);
        this.setBloodGroup(bloodGroup);
        this.setNic(nic);
    }

    public Licence() {
    }


    public String getNumber() {
        return getlNumber();
    }

    public void setNumber(String number) {
        this.setlNumber(number);
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public NicEntity getNic() {
        return nic;
    }

    public void setNic(NicEntity nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Licence{" +
                "lNumber='" + getlNumber() + '\'' +
                ", bloodGroup='" + getBloodGroup() + '\'' +
                ", nic=" + getNic() +
                '}';
    }

    public String getlNumber() {
        return lNumber;
    }

    public void setlNumber(String lNumber) {
        this.lNumber = lNumber;
    }
}
