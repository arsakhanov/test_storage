package com.example.demo.dao;


import com.example.demo.model.Product;
import com.example.demo.utils.MyUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;


import java.util.List;

public class ProductDAO {

    private final Session session = MyUtils.createHibernateSession();

    public Product getProduct(Long productId) {
        return null;
    }

    public Product addProduct(Product product) {
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            session.flush();
            transaction.commit();
        }
        return product;
    }

    public void updateProduct(Product product) {
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            Product newProduct = session.find(Product.class, product.getId());
            newProduct.setName(product.getName());
            newProduct.setVendorCode(product.getVendorCode());
            newProduct.setLastPurchasePrice(product.getLastPurchasePrice());
            newProduct.setLastSalePrice(product.getLastSalePrice());
            newProduct.setStorage(product.getStorage());
            session.update(newProduct);
/*            String updateSql = "update product set product_name = " + product.getName() +", vendor_code = " + product.getVendorCode() +
                    ", last_purchase_price = " + product.getLastPurchasePrice() + ", last_sale_price = " + product.getLastSalePrice() +
                    ", storage =" + product.getStorage() + " where product_id = "+ product.getId();
            session.createSQLQuery(updateSql);*/
            session.flush();
            transaction.commit();
        }
    }

    public boolean deleteProduct(Long productId) {
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            Product product = session.find(Product.class, productId);
            if (product != null) {
                session.delete(product);
                session.flush();
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<Product> getAllProducts() {

        String sql = "select product_id, vendor_code, product_name, last_purchase_price, last_sale_price, id_storage from Product";
        NativeQuery query = session.createSQLQuery(sql).addEntity(Product.class);
        return query.list();
    }

}
