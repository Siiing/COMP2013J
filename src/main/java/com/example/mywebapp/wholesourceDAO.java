package main.java.com.example.mywebapp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.lang.reflect.Method;
import java.util.List;

public class wholesourceDAO {

    public static List<wholesource> getAllSources() {
        List<wholesource> sourceList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sourceList = session.createQuery("from wholesource").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sourceList;
    }

    public static void saveSource(wholesource wholesource) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(wholesource);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }
    public static boolean deleteSourceByYear(String sourceOfWealth, int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String wealthColumn = "Wealth" + year;
            String deleteQuery = "UPDATE wholesource SET " + wealthColumn + " = 0 WHERE SourceOfWealth = :sourceOfWealth";

            Query query = session.createQuery(deleteQuery);
            query.setParameter("sourceOfWealth", sourceOfWealth);

            int rowsAffected = query.executeUpdate();
            if (rowsAffected > 0) {
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateSource(String sourceOfWealth, int year, int wealth) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            wholesource wholesource = session.get(wholesource.class, sourceOfWealth);
            if (wholesource != null) {
                String wealthColumn = "Wealth" + year;
                Method setterMethod = wholesource.class.getMethod("set" + wealthColumn, int.class);
                setterMethod.invoke(wholesource, wealth);

                session.update(wholesource);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertSource(String sourceOfWealth, int wealth2018, int wealth2019, int wealth2020, int wealth2021, int wealth2022) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            wholesource wholesource = new wholesource();
            wholesource.setSourceOfWealth(sourceOfWealth);
            wholesource.setWealth2018(wealth2018);
            wholesource.setWealth2019(wealth2019);
            wholesource.setWealth2020(wealth2020);
            wholesource.setWealth2021(wealth2021);
            wholesource.setWealth2022(wealth2022);

            session.save(wholesource);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
