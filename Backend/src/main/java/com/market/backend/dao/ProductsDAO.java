package com.market.backend.dao;

import com.market.backend.models.Product;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductsDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductsDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public List<Product> findAll(){
        return jdbcTemplate.query("SELECT * FROM product", new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(int id){
        return jdbcTemplate.query("SELECT * FROM product WHERE product_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);
    }

    public void create(Product product){
        List<Product> products=findAll();
        int id=0;
        for(int i=0;i<products.size();i++){
            if(i==products.size()-1){
               id=products.get(i).getId();
            }
        }
        id++;
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        jdbcTemplate.update("INSERT INTO product VALUES (?,?,?,?,?)",id,product.getName(),product.getQuantity(),
                product.getCreatedAt(),product.getUpdatedAt());
    }

    public void update(Product product,int id){
        Product oldProduct=findById(id);
        product.setCreatedAt(oldProduct.getCreatedAt());
        product.setUpdatedAt(LocalDateTime.now());
        jdbcTemplate.update("UPDATE product SET name=?,quantity=?,created_at=?,updated_at=? WHERE product_id=?",
                product.getName(),product.getQuantity(),product.getCreatedAt(),product.getUpdatedAt(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM product WHERE product_id=?",id);
    }

    public void fastUpdateUsingBatch(List<Product> products){

        long before=System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO product(id,name,quantity,created_at,updated_at) VALUES (?,?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,products.get(i).getId());
                ps.setString(2,products.get(i).getName());
                ps.setInt(3,products.get(i).getQuantity());
                ps.setString(4,products.get(i).getCreatedAt().toString());
                ps.setString(5, products.get(i).getUpdatedAt().toString());
            }

            @Override
            public int getBatchSize() {
                return products.size();
            }
        });

        long after=System.currentTimeMillis();

        System.out.println("Times with Batch: "+(after-before));
    }
}