package OrderService.Repositories;

import OrderService.DatabaseModels.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    public List<Child> findByFirstNameAndPatronymicAndLastName(String FirstName, String Patronymic, String LastName);

    default List<Child> Find(String FirstName, String Patronymic, String LastName) {
        return findByFirstNameAndPatronymicAndLastName(FirstName, Patronymic, LastName);
    }
}
