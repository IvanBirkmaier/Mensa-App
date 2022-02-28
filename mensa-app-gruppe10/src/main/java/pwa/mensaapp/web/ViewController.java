package pwa.mensaapp.web;

import org.springframework.web.bind.annotation.GetMapping;
@org.springframework.stereotype.Controller
public class ViewController {
      @GetMapping("/")
    public String startseite() {
        return "startseite";
    }

}
