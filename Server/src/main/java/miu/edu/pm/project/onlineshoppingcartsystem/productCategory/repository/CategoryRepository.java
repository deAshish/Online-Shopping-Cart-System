package miu.edu.pm.project.onlineshoppingcartsystem.productCategory.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.productCategory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
