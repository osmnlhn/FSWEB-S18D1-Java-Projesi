package dao;

import exceptions.BurgerException;
import entity.BreadType;
import entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class BurgerDaoImpl implements BurgerDao{

    private final EntityManager entityManager;
    @Autowired
    public BurgerDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {
       TypedQuery<Burger> selectResult= entityManager.createQuery("Selecet b from Burger b ",Burger.class);
        return selectResult.getResultList();
    }

    @Override
    public Burger findById(long id) {
        Burger burger=entityManager.find(Burger.class,id);
        if(burger==null){
            throw new BurgerException("Burger with given id is not exist"+id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Override
    public Burger remove(long id) {
        Burger foundBurger=findById(id);
        entityManager.remove(foundBurger);
        return foundBurger;
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
       TypedQuery<Burger> foundByPrice= entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>:price ORDER BY b.price desc",Burger.class);
       foundByPrice.setParameter("price",price);
        return foundByPrice.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> foundBreadType= entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType>:breadType ORDER BY b.breadType desc",Burger.class);
        foundBreadType.setParameter("breadType",breadType);
        return foundBreadType.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> foundContent=entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%,:content,'%') ORDER BY b.name",Burger.class);
        foundContent.setParameter("content",content);
        return foundContent.getResultList();
    }
}
