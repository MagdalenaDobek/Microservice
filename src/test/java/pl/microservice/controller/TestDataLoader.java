package pl.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.microservice.model.MessageService;
import pl.microservice.model.Message;

@Component
class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private MessageService messageService;


    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Message message1 = new Message("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Message message2 = new Message("Praesent malesuada est in metus rhoncus, vel accumsan tortor blandit.");
        Message message3 = new Message("Donec vel quam fringilla, condimentum felis volutpat, tempus ligula.");
        Message message4 = new Message("Nam in tellus porttitor, consectetur risus quis, feugiat eros.");
        Message message5 = new Message("Vivamus lacinia ligula ut odio cursus consequat.");
        Message message6 = new Message("Nullam at velit in nunc posuere pellentesque id nec tortor.");
        Message message7 = new Message("Aliquam lobortis dolor venenatis laoreet venenatis.");
        Message message8 = new Message("Mauris ut velit eu neque congue varius.");
        Message message9 = new Message("Praesent fermentum ipsum vel libero iaculis ultricies.");
        Message message10 = new Message("Etiam mattis odio vel ex eleifend ullamcorper dapibus ut nunc.");
        Message message11 = new Message("Sed rutrum eros et mi malesuada imperdiet.");
        Message message12 = new Message("Integer in leo tincidunt, laoreet dolor ut, laoreet nunc.");
        Message message13 = new Message("Nam id quam quis mauris venenatis ullamcorper at at diam.");
        Message message14 = new Message("Nulla eu lectus ornare, mattis neque eget, vehicula ligula");
        Message message15 = new Message("Donec sollicitudin neque a molestie dictum.");
        Message message16 = new Message("Donec sollicitudin neque a molestie dictum.");
        Message message17 = new Message("Quisque faucibus tortor vitae massa pharetra volutpat.");
        Message message18 = new Message("Nam at nulla aliquam, euismod arcu a, pharetra arcu.");
        Message message19 = new Message("Etiam nec mi et lectus interdum laoreet quis non justo.");
        Message message20 = new Message("Ut eu orci a felis feugiat dictum et eget turpis.");

        messageService.save(message1);
        messageService.save(message2);
        messageService.save(message3);
        messageService.save(message4);
        messageService.save(message5);
        messageService.save(message6);
        messageService.save(message7);
        messageService.save(message8);
        messageService.save(message9);
        messageService.save(message10);
        messageService.save(message11);
        messageService.save(message12);
        messageService.save(message13);
        messageService.save(message14);
        messageService.save(message15);
        messageService.save(message16);
        messageService.save(message17);
        messageService.save(message18);
        messageService.save(message19);
        messageService.save(message20);

    }

}
