package pwa.mensaapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import pwa.mensaapp.objects.MensaOverview;

import java.time.LocalDate;

@org.springframework.stereotype.Service
public class ObjectService {
    @Autowired
    EntityService entityService;
    @Autowired
    ApiService apiService;

    public MensaOverview mensaOverview;


    public MensaOverview infoOfOneMensa(Long id, LocalDate date){
        mensaOverview = new MensaOverview();
        mensaOverview.setName(entityService.findById(id).getName());
        mensaOverview.setAdresse(entityService.findById(id).getAdresse());
        if (apiService.openingStatus(id, date) == true){
            mensaOverview.setOffenJaNein("Heute geschlossen");
        }else{
            mensaOverview.setOffenJaNein("Geöffnet");
        }
        mensaOverview.setGerichte(apiService.allMealsPerDay(id, date));
        return mensaOverview;
    }








}
