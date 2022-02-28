package pwa.mensaapp.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCanteen extends CrudRepository<CanteenEntity, Long>{}