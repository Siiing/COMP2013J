package main.java.com.example.mywebapp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
//新加表的DAO
public class resultDAO {
    public static List<Integer> getAllResult() {
        List<Integer> resultList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            resultList = session.createQuery("from result").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;

    }

    public static void saveResult(result result) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(result);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    public  static  void updateResultDetails(Integer PID, Integer Rank, String Wealth, Integer Year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();


                String updateQuery = "";
                if(Rank == null){
                    updateQuery =  "UPDATE result SET Wealth = :Wealth WHERE PID = :PID AND Year = :Year";

                }
                if(Wealth == null){
                    updateQuery =  "UPDATE result SET Rank = :Rank WHERE PID = :PID AND Year = :Year";

                }
                Query query = session.createQuery(updateQuery);
                if(Rank == null){
                    query.setParameter("Wealth", Wealth);
                }
                if(Wealth == null){
                    query.setParameter("Rank", Rank);
                }
                query.setParameter("PID", PID);
                query.setParameter("Year", Year);
                query.executeUpdate();
                transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean deleteResult(int PID,int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String updateQuery =  "DELETE FROM result  WHERE PID = :PID AND Year = :Year";
            Query query = session.createQuery(updateQuery);
            query.setParameter("PID", PID);
            query.setParameter("Year", year);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteResultByPid(int PID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String updateQuery =  "DELETE FROM result  WHERE PID = :PID ";
            Query query = session.createQuery(updateQuery);
            query.setParameter("PID", PID);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean insertResult(int PID, int Rank, String Wealth, int Year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            result result = new result();
            result.setPID(PID);
            result.setRank(Rank);
            result.setWealth(Wealth);
            result.setYear(Year);
            session.save(result);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
