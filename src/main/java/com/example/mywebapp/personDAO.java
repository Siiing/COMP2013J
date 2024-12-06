package main.java.com.example.mywebapp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class personDAO {
    public static List<Integer> getAllPerson() {
        List<Integer> personList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            personList = session.createQuery("from person").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personList;
    }

    public static void savePerson(person person) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    public static void updatePersonDetails(int PID, String EnglishName, String newFavoriteFood, String newCountry,String source) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            person person = session.get(person.class, PID);
            if (person != null) {
//                person.setEnglishName(EnglishName);
                person.setFood(newFavoriteFood);
                person.setCountry(newCountry);
                person.setSource(source);
                session.update(person);
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("Person with PID " + PID + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean deletePerson(int PID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            person person = session.get(person.class, PID);
            if (person != null) {
                session.delete(person);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("Person with PID " + PID + " not found");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean insertPerson(int PID, String EnglishName, String ChineseName, String country, String favoriteFood,String source) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            person person = new person();
            person.setPID(PID);
            person.setEnglishName(EnglishName);
            person.setChineseName(ChineseName);
            person.setCountry(country);
            person.setFood(favoriteFood);
            person.setSource(source);
            session.save(person);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

