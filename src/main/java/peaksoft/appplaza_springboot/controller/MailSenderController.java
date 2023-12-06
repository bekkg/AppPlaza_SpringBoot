package peaksoft.appplaza_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.service.MailSenderService;

import java.util.List;

@RestController
@RequestMapping("api/mailSenders")
@RequiredArgsConstructor
public class MailSenderController {

    private final MailSenderService senderService;

    @PostMapping("/sign-in")
    public ResponseEntity<MailSenderResponse> add(@RequestBody MailSenderRequest request){
        MailSenderResponse response = senderService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public MailSenderResponse findById (@PathVariable Long id){
        return senderService.findById(id);
    }
    @GetMapping
    public List<MailSenderResponse> findAll(){
        return senderService.findAll();
    }
    @PutMapping("/update/{id}")
    public MailSenderResponse update (@PathVariable ("id") Long id, @RequestBody MailSenderRequest request){
        return senderService.update(id,request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        senderService.delete(id);
        return "MailSender with id: " + id + " successfully deleted";
    }
}
