package com.example.demo.dao;


import com.example.demo.model.Product;
import com.example.demo.model.Storage;
import com.example.demo.utils.MyUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class StorageDAO {

    private final Session session = MyUtils.createHibernateSession();

    public Storage getStorage(String storageName){
        return null;
    }

    public Storage addStorage(Storage storage){
        if(session!=null){
            Transaction transaction = session.beginTransaction();
            session.save(storage);
            session.flush();
            transaction.commit();
        }
        return storage;
    }

    public boolean addProductInStorage(String storageName, List<Product> products){
        Transaction transaction = session.beginTransaction();
        Query<Storage> query = session.createQuery("from Storage s where s.name = :name");
        query.setParameter("name", storageName);
        Storage storage = query.getSingleResult();
        if(storage != null){
            storage.setProducts(products);
            for (Product product:products) {
                product.setStorage(storage);
                session.save(product);
            }
            session.saveOrUpdate(storage);
            session.flush();
            transaction.commit();
            return true;
        } else {
            return false;
        }
    }

    public Storage updateStorage(Storage storage){
        Storage newStorage = session.find(Storage.class, storage.getId());
        Storage oldStorage = newStorage;
        if(session!=null){
            Transaction transaction = session.beginTransaction();
            newStorage.setName(storage.getName());
            if(storage.getProducts() != null){
                newStorage.setProducts(storage.getProducts());
            }
            session.update(newStorage);
            session.flush();
            transaction.commit();
            return newStorage;
        }
        return oldStorage;
    }

    public boolean deleteStorage(String storageName){
        return false;
    }

    public List<Storage> getAllStorages(){
        String sql = "select storage_id, storage_name from storage";
        Query<Storage> query = session.createSQLQuery(sql).addEntity(Storage.class);
        return query.list();
    }
}
