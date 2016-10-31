package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<T extends IEntity<ID>, ID extends Serializable> {
    T save(T entity);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    T findOne(ID id);

    void delete(ID id);
}
