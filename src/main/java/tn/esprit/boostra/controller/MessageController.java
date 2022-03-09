package tn.esprit.boostra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Message;
import tn.esprit.boostra.entity.MessageDTO;
import tn.esprit.boostra.entity.MessageDTO2;
import tn.esprit.boostra.service.MessageService;

@RestController
@RequestMapping("msgs")
@CrossOrigin(value = "*")
public class MessageController {

  @Autowired MessageService messageservice;

  @PostMapping
  public void addChat(@RequestBody MessageDTO msg) {
    messageservice.addChat(msg.getContent(), msg.getIdsender(),
                           msg.getIdreciver());
  }
  @GetMapping("{idSender}/{idReceiver}")
  public List<Message> getMessage(@PathVariable int idSender,
                                  @PathVariable int idReceiver) {
    return messageservice.getMessage(idSender, idReceiver);
  }
  @GetMapping("{id}")
  public List<MessageDTO2> getlastMessage(@PathVariable int id) {
    return messageservice.getLastMessage(id);
  }

  @DeleteMapping("{idMsg}")
  public ResponseEntity<Void> deleteChat(@PathVariable int idMsg) {

    boolean m = messageservice.deleteChat(idMsg);
    if (m)
      return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
