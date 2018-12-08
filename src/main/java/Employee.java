import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "idemployees")
    private int id;
    @Column(name = "`first name`")
    private String firstName;
    @Column(name = "`last name`")
    private String lastName;

    @OneToOne
    private Phone phone;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Laptop> laptops = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
//        return "Employee{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", phone=" + phone +
//                '}';
        return "id: " + id + " | " + firstName + " " + lastName + "\n" +
                "phone: " + phone + "\n" +
                "laptop: " + laptops + "\n"+
                "cars: " + cars + "\n";

    }
}
