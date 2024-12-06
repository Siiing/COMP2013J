package main.java.com.example.mywebapp;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.lang.reflect.Method;
import java.util.List;

public class rankDAO {

    public static List<rank> getAllRanks() {
        List<rank> rankList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            rankList = session.createQuery("from rank").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rankList;
    }

    public static void saveRank(rank rank) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(rank);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    public static boolean insertRanks(String EnglishName, String ChineseName, int rank2018, int rank2019, int rank2020, int rank2021, int rank2022) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "INSERT INTO `rank`(`EnglishName`, `ChineseName`, `rank2018`, `rank2019`, `rank2020`, `rank2021`, `rank2022`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Transaction transaction = session.beginTransaction();

            // use createSQLQuery rather than createQuery
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, EnglishName);
            query.setParameter(1, ChineseName);
            query.setParameter(2, rank2018);
            query.setParameter(3, rank2019);
            query.setParameter(4, rank2020);
            query.setParameter(5, rank2021);
            query.setParameter(6, rank2022);

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

    public static boolean deleteRankByYear(String EnglishName, int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String rankColumn = "Rank" + year;
            String deleteQuery = "UPDATE `rank` SET " + rankColumn + " = 0 WHERE EnglishName = :EnglishName";

            Query query = session.createSQLQuery(deleteQuery);
            query.setParameter("EnglishName", EnglishName);

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

    public static boolean updateRank(String EnglishName, int year, int rank) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            rank rankObj = session.get(rank.class, EnglishName);
            if (rankObj != null) {
                String rankColumn = "Rank" + year;
                Method setterMethod = rank.class.getMethod("set" + rankColumn, int.class);
                setterMethod.invoke(rankObj, rank);

                session.update(rankObj);
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

    public boolean insertRank(String EnglishName, String ChineseName, int rank2018, int rank2019, int rank2020, int rank2021, int rank2022) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            rank rank = new rank();
            rank.setEnglishName(EnglishName);
            rank.setChineseName(ChineseName);
            rank.setRank2018(rank2018);
            rank.setRank2019(rank2019);
            rank.setRank2020(rank2020);
            rank.setRank2021(rank2021);
            rank.setRank2022(rank2022);
            session.save(rank);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean upodateRanks(String EnglishName, int year , int newMoney) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String rank = "Rank"+year;
            String sql = "UPDATE `rank` SET "+rank+" = ? WHERE `EnglishName` =? ";
            Transaction transaction = session.beginTransaction();

            // 使用 createSQLQuery 而不是 createQuery
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, newMoney);
            query.setParameter(1, EnglishName);


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

}
