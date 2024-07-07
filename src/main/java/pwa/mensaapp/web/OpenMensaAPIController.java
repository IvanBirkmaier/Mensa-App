package pwa.mensaapp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pwa.mensaapp.service.ApiService;

@RestController
public class OpenMensaAPIController {

    @Autowired
    private ApiService apiService;


    @GetMapping("/canteens")
    public void getListOfAllCanteensAPIcall() {
        apiService.getListOfAllCanteens();
    }
}
