package app.service.impl;

import app.dto.ProductDTO;
import app.message.MessageSender;
import app.message.MessageStatus;
import app.message.ProductMessage;
import app.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {


    @Override
    public void sendCreateMessage(Map<Integer, List<Integer>> productMap) {

    }

    @Override
    public void sendUpdateMessage(ProductDTO productDTO) {

    }
}