/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class HibernateUtil {
 private static SessionFactory sessionFactory;
 
 public static SessionFactory getSessionFactory() {
  if (sessionFactory == null) {
   StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
     .configure("hibernate.cfg.xml").build();
   Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
   sessionFactory = metadata.getSessionFactoryBuilder().build();
  }
  return sessionFactory;
 }
 
}