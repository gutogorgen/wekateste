package augusto.devgridteste.wekatest.server.repository;

import augusto.devgridteste.wekatest.server.model.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientJpaRepository extends JpaRepository<ClientRequest, Long> {

}
