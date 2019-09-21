package t1708e.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1708e.hellospring.dto.HeroDTO;
import t1708e.hellospring.entity.Hero;
import t1708e.hellospring.entity.rest.RESTPagination;
import t1708e.hellospring.entity.rest.RESTResponse;
import t1708e.hellospring.service.HeroService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "1", required = false) int page,
                                          @RequestParam(defaultValue = "10", required = false) int limit) {
        Page<Hero> heroPage = heroService.heroesWithPaginate(page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(heroPage.getContent().stream().map(x -> new HeroDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, heroPage.getTotalPages(), heroPage.getTotalElements()))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Hero hero = heroService.getById(id);
        if (hero == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new HeroDTO(heroService.getById(id)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Hero hero) {
        // validate.
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .addData(new HeroDTO(heroService.create(hero)))
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Hero updateHero) {
        Hero existHero = heroService.getById(id);
        if (existHero == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        existHero.setName(updateHero.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new HeroDTO(heroService.update(existHero)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Hero existHero = heroService.getById(id);
        if (existHero == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        heroService.delete(existHero);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Simple Success")
                .build(),
                HttpStatus.OK);
    }
}
