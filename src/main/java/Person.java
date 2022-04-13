import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    private String first_name;
    private String last_name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
