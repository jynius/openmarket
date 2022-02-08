package us.jyni.thrthn.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GenericRepository<E, I> extends JpaSpecificationExecutor<E>, JpaRepository<E, I> {

}