package rikkei.academy.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Catalog;
import rikkei.academy.repository.ICatalogRepository;

import java.util.Optional;
@Service
public class CatalogService implements ICatalogService{
    @Autowired
    private ICatalogRepository catalogRepository;
    @Override
    public Page<Catalog> findAll(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogRepository.findById(id);
    }

    @Override
    public void save(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    public void remove(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Iterable<Catalog> findAll() {
        return catalogRepository.findAll();
    }
}
