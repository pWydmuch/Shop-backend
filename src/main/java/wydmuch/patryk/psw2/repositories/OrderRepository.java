package wydmuch.patryk.psw2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wydmuch.patryk.psw2.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
