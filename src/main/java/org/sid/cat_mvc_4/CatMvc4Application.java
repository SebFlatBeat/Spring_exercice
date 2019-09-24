package org.sid.cat_mvc_4;

import org.sid.cat_mvc_4.dao.ProduitRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CatMvc4Application {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(CatMvc4Application.class, args);
        ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);


        produitRepository.findAll().forEach(produit -> System.out.println(produit.getDesignation()));
    }

}
