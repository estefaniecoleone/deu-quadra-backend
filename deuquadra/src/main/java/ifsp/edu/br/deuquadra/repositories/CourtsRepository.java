package ifsp.edu.br.deuquadra.repositories;

import ifsp.edu.br.deuquadra.models.CompaniesModel;
import ifsp.edu.br.deuquadra.models.CourtsModel;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@ComponentScan("api.culturaprincipal.*")
@Repository
public interface CourtsRepository extends JpaRepository<CourtsModel, Integer> {
	@Query("FROM CourtsModel q INNER JOIN tb_empresa e ON e.idEmpresa = q.id_empresa WHERE"+
			"(e.lon BETWEEN ?3 AND ?4) AND (e.lat BETWEEN ?1 AND ?2")
	List<CourtsModel>findByProximity(Double minLat, Double maxLat, Double minLon, Double maxLon);
}
