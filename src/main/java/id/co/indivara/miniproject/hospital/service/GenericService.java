package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;


public class GenericService <T>{
    @Autowired
    private GenericRepository<T> genericRepository;

    //CREATE NEW DATA
    public T saveData (T entity) {
        return genericRepository.save(entity);
    }

    //UPDATE DATA BY ID
    public T updateData(Long id, T entity){
        T empEntity = genericRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Data tidak ditemukan!"));
        return genericRepository.save(entity);
    }

    //FIND ALL DATA
    public List<T> findAll(){
        return (List<T>) genericRepository.findAll();
    }

    //FIND DATA BY ID
    public T findById(Long id){
        return genericRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Data tidak ditemukan!"));
    }

    //DELETE DATA BY ID
    public void deletebyId(Long id){
        T empEntity = genericRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Data tidak ditemukan!"));
        genericRepository.deleteById(id);
    }
}
