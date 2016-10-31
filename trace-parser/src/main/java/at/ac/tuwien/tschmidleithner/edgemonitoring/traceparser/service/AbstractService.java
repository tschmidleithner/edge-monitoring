package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract service to provide basic CRUD operations.
 */
@Transactional
public abstract class AbstractService<T extends IEntity<ID>, ID extends Serializable> implements ICrudService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    @Transactional
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().delete(id);
    }
}
