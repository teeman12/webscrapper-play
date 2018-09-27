package ratefinder.repository;

import ratefinder.bean.SearchEntry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchEntryRepository extends CrudRepository<SearchEntry, Long> {

}
