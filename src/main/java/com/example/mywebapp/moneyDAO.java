package main.java.com.example.mywebapp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class moneyDAO {

    public static List<money> getAllMoney() {
        List<money> moneyList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            moneyList = session.createQuery("from money").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moneyList;
    }

    public static void saveMoney(money m) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(m);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    public void updateWealthOfYear(String name, int year, int newWealth) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String wealthColumn = "Wealth" + year;
            String updateQuery = "UPDATE money SET " + wealthColumn + " = :newWealth WHERE EnglishName = :name";

            Query query = session.createQuery(updateQuery);
            query.setParameter("newWealth", newWealth);
            query.setParameter("EnglishName", name);

            int result = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean deleteMoneyByYear(String id, int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String wealthColumn = "Wealth" + year;
            String deleteQuery = "UPDATE money SET " + wealthColumn + " = 0 WHERE EnglishName = :id";

            Query query = session.createQuery(deleteQuery);
            query.setParameter("id", id);
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


    public static boolean updateMoney(String id, int year, int wealth) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            money money = session.get(money.class, id);
            if (money != null) {
                if (year == 2018) {
                    money.setWealth2018(wealth);
                } else if (year == 2019) {
                    money.setWealth2019(wealth);
                } else if (year==2020) {
                    money.setWealth2020(wealth);
                } else if (year==2021) {
                    money.setWealth2021(wealth);
                } else if (year==2022) {
                    money.setWealth2022(wealth);
                }

                session.update(money);
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

    public static boolean insertMoney( String name, String chinesename, int wealth2018, int wealth2019,int wealth2020,int wealth2021,int wealth2022 ) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            money money = new money();
            money.setEnglishName(name);
            money.setChineseName(chinesename);
            money.setWealth2018(wealth2018);
            money.setWealth2019(wealth2019);
            money.setWealth2020(wealth2020);
            money.setWealth2021(wealth2021);
            money.setWealth2022(wealth2022);
            session.save(money);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
