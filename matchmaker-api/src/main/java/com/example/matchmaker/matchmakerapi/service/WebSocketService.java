package com.example.matchmaker.matchmakerapi.service;

import com.example.matchmaker.matchmakerapi.entity.Conversa;
import com.example.matchmaker.matchmakerapi.entity.Mensagem;
import com.example.matchmaker.matchmakerapi.entity.repository.ConversaRepository;
import com.example.matchmaker.matchmakerapi.service.dto.request.MensagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSocketService {

  private final SimpMessagingTemplate template;
  private final ConversaRepository conversaRepository;

  public void sendMessage(MensagemRequest message) {
    template.convertAndSend("/topic/message", message.getCorpoMensagem());
  }

  public void sendMessageToUser(Mensagem message, String username) {
    Optional<Conversa> conversa = conversaRepository.findByIdConversaAndDeletedFalse(message.getIdConversa());

    if (conversa.isPresent()) {
      if (conversa.get().getIdUsuarioLogado().equals(username)) {
        username = conversa.get().getIdUsuarioLogado();
      } else {
        username = conversa.get().getIdUsuarioConversa();
      }
    }

    String destination = String.format("/topic/message", username);

    template.convertAndSend(destination, message.getCorpoMensagem());
  }

  public void updateMessage(MensagemRequest message) {
    template.convertAndSend("/topic/message", message.getCorpoMensagem());
  }
}
