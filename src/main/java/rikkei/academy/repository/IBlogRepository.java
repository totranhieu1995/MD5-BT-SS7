package rikkei.academy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Blog;
@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog,Long> {
}
