package controller;

import dao.BurgerDao;
import entity.BreadType;
import entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.BurgerValidation;

import java.util.List;

@RestController
@RequestMapping

public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController (BurgerDao burgerDao ){
        this.burgerDao=burgerDao;
    }
    @PostMapping
    public Burger save(@RequestBody Burger burger){
        BurgerValidation.checkName(burger.getContents());
        return burgerDao.save(burger);
    }

    @GetMapping
    public List <Burger>findAll(){
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger find(@PathVariable("id") long id){
        return burgerDao.findById(id);
    }

    @PutMapping("/{id}")
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @GetMapping("/breadType/{breadType}")
    public List <Burger>findBreadType(@PathVariable("breadType") String breadType){
        BreadType bt=BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bt);
    }
    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Integer price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String content){
        return burgerDao.findByContent(content);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable("id") long id){
        return burgerDao.remove(id);
    }
}
