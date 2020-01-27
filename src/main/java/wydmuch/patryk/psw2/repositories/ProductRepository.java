package wydmuch.patryk.psw2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wydmuch.patryk.psw2.entity.Category;
import wydmuch.patryk.psw2.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   List<Product> findByCategory(Category category);
}
