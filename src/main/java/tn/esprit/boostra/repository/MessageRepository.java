package tn.esprit.boostra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Message;

@Repository

public interface MessageRepository extends CrudRepository<Message, Long> {

  @Query(
      "select m from Message m where (m.sender.id=:idSender and m.receiver.id=:idReceiver) or"
      +
      " (m.sender.id=:idReceiver and m.receiver.id=:idSender) order by date asc")
  List<Message>
  findBySenderAndReceiver(@Param("idSender") long idSender,
                          @Param("idReceiver") long idReceiver);

  @Query(
      "select m from Message m where (m.sender.id=:idSender and m.receiver.id=:idReceiver) or"
      +
      " (m.sender.id=:idReceiver and m.receiver.id=:idSender) order by date desc")
  List<Message>
  findBySenderAndReceiverdesc(@Param("idSender") long idSender,
                              @Param("idReceiver") long idReceiver);
}