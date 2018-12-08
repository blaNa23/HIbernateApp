import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "laptops")
public class Laptop implements Serializable {

    @Id
    @Column(name = "idlaptops")
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
//        return "Laptop{" +
//                "id=" + id +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                '}';
        return "id: " + id + " | " + brand + " " + model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return id == laptop.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
