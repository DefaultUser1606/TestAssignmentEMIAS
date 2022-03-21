package OrderService.DatabaseModels;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Children")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String firstName;
    private String patronymic;
    private String lastName;

    public Child() {}

    Child(String firstName, String patronymic, String lastName) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Child))
            return false;
        Child child = (Child)o;
        return Objects.equals(this.Id, child.Id) &&
                Objects.equals(this.firstName, child.firstName) &&
                Objects.equals(this.patronymic, child.patronymic) &&
                Objects.equals(this.lastName, child.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.Id, this.firstName, this.patronymic, this.lastName);
    }

    @Override
    public String toString() {
        return String.format(
                "Child{Id=%d, FirstName='%s', Patronymic='%s', LastName='%s'}",
                this.Id,
                this.firstName,
                this.patronymic,
                this.lastName);
    }
}
