package pwa.mensaapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pwa.mensaapp.objects.MensaOverview;
import pwa.mensaapp.persistence.CanteenEntity;
import pwa.mensaapp.service.EntityService;
import pwa.mensaapp.service.ObjectService;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CanteenController {


    @Autowired
    EntityService entityService;
    @Autowired
    ObjectService objectService;


    @GetMapping(value = "/canteensjsonlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CanteenEntity> markers (){
        return entityService.findAll();
    }



    @GetMapping(value = "/mensa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MensaOverview mensaOverviewEntity (@PathVariable String id ) {
        Long idl = Long.parseLong(id);
        LocalDate date = LocalDate.of(2021,9,11);
        return objectService.infoOfOneMensa(idl, date);
    }
}




