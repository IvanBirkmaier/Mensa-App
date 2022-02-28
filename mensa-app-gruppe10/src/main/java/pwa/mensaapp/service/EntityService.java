package pwa.mensaapp.service;




import org.springframework.beans.factory.annotation.Autowired;
import pwa.mensaapp.persistence.CanteenEntity;
import pwa.mensaapp.persistence.RepoCanteen;



import java.util.*;


@org.springframework.stereotype.Service
public class EntityService {

    @Autowired
    private RepoCanteen repoCanteen;

    public List<CanteenEntity> findAll() {
        var iterator = repoCanteen.findAll();
        var canteens = new ArrayList<CanteenEntity>();
        for (CanteenEntity p : iterator) {
            canteens.add(p);
        }
        return canteens;
    }


    public CanteenEntity findById (Long id){
       var p = repoCanteen.findById(id).get();
       return p;
    }





}
