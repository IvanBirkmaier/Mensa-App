package pwa.mensaapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pwa.mensaapp.persistence.CanteenEntity;
import pwa.mensaapp.persistence.RepoCanteen;
import pwa.mensaapp.response.RespondeOpeningStatus;
import pwa.mensaapp.response.ResponseCanteen;
import pwa.mensaapp.response.ResponseMeals;
import java.time.LocalDate;
import java.util.*;


@org.springframework.stereotype.Service
public class ApiService {
    @Autowired
    private RepoCanteen repoCanteen;
    @Autowired
    private RestTemplate restTemplate;
    private final String BASE_URL_OPENMENSA_API = "https://openmensa.org/api/v2";



    //Zieht sich eine Liste mit allen Kantinendatensätzen, schreibt diese dann um zu einer Java Entity klasse und speichert alle Datensätze ab
   // TO-DO else weg machen dass man nur kantinen mit adresse hat datensortierung einbauen
    public void getListOfAllCanteens(){
        String url = BASE_URL_OPENMENSA_API + "/canteens";
        ResponseEntity<ResponseCanteen[]> canteensResponse = restTemplate.getForEntity(url, ResponseCanteen[].class);
        ResponseCanteen[] canteens = canteensResponse.getBody();
        var iterator = canteens;
        for (ResponseCanteen responseCanteen : iterator){
            Long id = Long.valueOf(responseCanteen.getId());
            String name = responseCanteen.getName();
            String city = responseCanteen.getCity();
            String address = responseCanteen.getAddress();
            List coordinates = (List) responseCanteen.getCoordinates();
            Double nearLAT = Double.valueOf(0);
            Double nearLNG = Double.valueOf(0);
            if (coordinates != null) {
                 nearLAT = (Double) coordinates.get(0);
                nearLNG = (Double) coordinates.get(1);
                repoCanteen.save(new CanteenEntity(id, name, city, address, nearLAT, nearLNG));
            }else {
                repoCanteen.save(new CanteenEntity(id, name, city, address, nearLAT, nearLNG));
            }
        }
    }



    //Holt sich für eine bestimmte Cantine den heutigen öffnungsstatus true = ZU, false = ÖFFEN
    public boolean openingStatus(Long id, LocalDate date){
        String url = BASE_URL_OPENMENSA_API + "/canteens/"+id+"/days/"+date;
        ResponseEntity<RespondeOpeningStatus> openingStatus = restTemplate.getForEntity(url, RespondeOpeningStatus.class);
        RespondeOpeningStatus openingStatusBody = openingStatus.getBody();
        boolean status = openingStatusBody.isClosed();
        return status;
    }

    //Funktion für Meals braucht days und id!!
    public List<ResponseMeals> allMealsPerDay(Long id, LocalDate date){
        String url = BASE_URL_OPENMENSA_API +"/canteens/"+id+"/days/"+date+"/meals";
        ResponseEntity<ResponseMeals[]> responseMeals = restTemplate.getForEntity(url, ResponseMeals[].class);
        ResponseMeals[] response = responseMeals.getBody();
        var iterator = response;
        var meals = new ArrayList<ResponseMeals>();
        for (ResponseMeals responseMeals1 :iterator
             ) {
            meals.add(responseMeals1);
        }
        return meals;
    }


}
