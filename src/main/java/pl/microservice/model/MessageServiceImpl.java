package pl.microservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Message> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<Message> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existById(UUID id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public Message save(Message entity) {
        return repository.save(entity);
    }

    @Override
    public List<Message> random(@PathVariable Integer count) {
        List<Message> all = repository.findAll();
        List<Message> result = new ArrayList<>();
        Set<Integer> generatedInt = new HashSet<>();

        int listSize = all.size();

        while (generatedInt.size() < count) {
            Random rand = new Random();
            int random = rand.nextInt(listSize-1);
            generatedInt.add(random);
        }


        if (listSize > count - 1 && count > 0) {
            for (Integer randInt : generatedInt) {
                result.add(all.get(randInt));
            }
        } else if (listSize == count - 1) {
            result = all;
        }

        return result;
    }

}
