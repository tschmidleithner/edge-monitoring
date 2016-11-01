package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<T extends IEntity<ID>, ID extends Serializable> {
    T save(T entity);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    List<T> findAll(Sort sort);

    T findOne(ID id);

    void delete(ID id);
}
