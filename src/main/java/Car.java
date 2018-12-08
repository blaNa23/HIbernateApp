import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @Column(name = "idcars")
    private int id;
    @Column(name = "`license plate`")
    private String licensePlate;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;

    @ManyToMany(mappedBy = "cars")
    private List<Employee> employees = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employee) {
        this.employees = employee;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
//        return "Car{" +
//                "id=" + id +
//                ", licensePlate='" + licensePlate + '\'' +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                '}';
        return "id: " + id + " | " + brand + " " + model + " , " + licensePlate;
    }
}
