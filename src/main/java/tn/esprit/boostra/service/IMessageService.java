package tn.esprit.boostra.service;

import java.util.List;
import tn.esprit.boostra.entity.Message;
import tn.esprit.boostra.entity.MessageDTO2;

public interface IMessageService {

  void addChat(String content, long idUser, long idReceiver);

  List<Message> getMessage(long idSender, long idReceiver);

  boolean deleteChat(long idMsg);

  List<MessageDTO2> getLastMessage(long id);
}
