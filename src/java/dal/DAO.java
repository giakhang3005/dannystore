/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Item;
import model.Orders;
import model.Products;
import model.Users;
import sideFunction.cartMethod;

/**
 *
 * @author nguye
 */
public class DAO extends DBContext {

    //------------------------------------------------------------    
    //CATEGORIES
    public List<Categories> getAllCategories() {
        String sql = "select * from Categories";
        List<Categories> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt("ID"),
                        rs.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Categories getCategoriesByCid(int cid) {
        String sql = "select * from Categories where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            boolean hasCategories = rs.next();
            if (hasCategories) {
                Categories c = new Categories(rs.getInt("ID"),
                        rs.getString("name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Categories addCategories(int id, String name) {
        String sql = "insert into Categories values(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            ResultSet rs = st.executeQuery();

            Categories c = new Categories(id, name);
            return c;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteCategories(int id) {
        String sql = "DELETE FROM [dbo].[Categories] WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateCategories(int id, String name) {
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [ID] = ?\n"
                + "      ,[name] = ?\n"
                + " WHERE id = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            st.setInt(3, id);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //------------------------------------------------------------    
    //PRODUCTS
    public List<Products> getAllProducts() {
        String sql = "select * from Products";
        List<Products> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("cid"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Products getProductsById(String id) {
        String sql = "select * from Products where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            boolean cond = rs.next();
            if (cond) {
                Products p = new Products(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("cid"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Products> getProductsByCid(int cid) {
        String sql = "select * from Products where cid = ?";
        List<Products> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("cid"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Products> searchProduct(String describe, int fromPrice, int toPrice, int cid) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from products where 1=1";

        if (describe != null && !describe.equals("")) {
            sql += " and describe like '%" + describe + "%' or name like '%" + describe + "%'";
        }
        if (fromPrice != -1) {
            sql += " and price >= " + fromPrice;
        }
        if (toPrice != -1) {
            sql += " and price <= " + toPrice;
        }
        if (cid != 0) {
            sql += " and cid = " + cid;
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("cid"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Products addProducts(String id, String name, String describe, int quantity, int price, String img, int cid) {
        String sql = "insert into Products values(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, describe);
            st.setInt(4, quantity);
            st.setInt(5, price);
            st.setString(6, img);
            st.setInt(7, cid);
            ResultSet rs = st.executeQuery();

            Products p = new Products(id, name, describe, quantity, price, img, cid);
            return p;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateProducts(String id, String name, String describe, int quantity, int price, String img, int cid) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ID] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE id = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, describe);
            st.setInt(4, quantity);
            st.setInt(5, price);
            st.setString(6, img);
            st.setInt(7, cid);
            st.setString(8, id);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteProducts(String id) {
        String sql = "DELETE FROM [dbo].[Products] WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //------------------------------------------------------------    
    //USERS
    public List<Users> getAllUsers() {
        String sql = "select * from Users";
        List<Users> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String password = "";
                if (rs.getInt("Role") == 0) {
                    password = "";
                } else {
                    password = rs.getString("password");
                }
                Users u = new Users(rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("Role"),
                        rs.getString("cart"),
                        rs.getDouble("balance"),
                        password);
                list.add(u);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Users getUsers(String username, String password) {
        String sql = "select * from Users where username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            boolean hasUser = rs.next();
            if (hasUser) {
                Users u = new Users(rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("Role"),
                        rs.getString("cart"),
                        rs.getDouble("balance"),
                        rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Users getUsersByUsername(String username) {
        String sql = "select * from Users where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            boolean hasUser = rs.next();
            if (hasUser) {
                Users u = new Users(rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("Role"),
                        rs.getString("cart"),
                        rs.getDouble("balance"),
                        rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Users addNewUser(String username, String name, String email, int role, String cart, double balance, String password) {
        String sql = "insert into Users values(?, ?, ?, ?, ?, ?, ?)";
        Users u = new Users(username, name, email, role, cart, balance, password);
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, name);
            st.setString(3, email);
            st.setInt(4, role);
            st.setString(5, cart);
            st.setDouble(6, balance);
            st.setString(7, password);
            ResultSet rs = st.executeQuery();
            return u;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return u;
    }

    public boolean updateUsers(String username, String name, String email, int role, String cart, double balance, String password) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [username] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[Role] = ?\n"
                + "      ,[cart] = ?\n"
                + "      ,[balance] = ?\n"
                + "      ,[password] = ?\n"
                + " WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, name);
            st.setString(3, email);
            st.setInt(4, role);
            st.setString(5, cart);
            st.setDouble(6, balance);
            st.setString(7, password);
            st.setString(8, username);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void setBalance(double balance, String username) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [balance] = ?\n"
                + " WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, balance);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean deleteUsers(String username) {
        String sql = "DELETE FROM [dbo].[Users] WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateCartToDB(String username, List<Item> cart) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "      SET[cart] = ?\n"
                + " WHERE username = ?";
        cartMethod cm = new cartMethod();
        String cartToString = cm.splitCart(cart);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cartToString);
            st.setString(2, username);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //-----------------------------------------
    //ORDER
    
    public Orders getOrderByOID(String oid) {
        String sql = "select * from Orders where oid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, oid);
            ResultSet rs = st.executeQuery();
            boolean hasOrder = rs.next();
            if (hasOrder) {
                Orders o = new Orders(rs.getString("OID"),
                        rs.getString("username"),
                        rs.getString("cart"),
                        rs.getString("date"),
                        rs.getDouble("totalPrice"));
                return o;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void addOrders(Orders o) {
        String sql = "insert into Orders values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, o.getOID());
            st.setString(2, o.getUsername());
            st.setString(3, o.getOrderCart());
            st.setString(4, o.getOrderDate());
            st.setDouble(5, o.getTotalMoney());
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<Orders> getAllOrdersByUsername(String username) {
        String sql = "select * from Orders where username = ?";
        List<Orders> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getString("OID"),
                        rs.getString("username"),
                        rs.getString("cart"),
                        rs.getString("date"),
                        rs.getDouble("totalPrice"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
