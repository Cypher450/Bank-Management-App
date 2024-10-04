package bank.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bank.app.entities.Contact;
 
@Repository
public class ContactDaoImpl implements ContactDao {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    // Getter and setter methods
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   
    @Override
    public int insertContact(Contact contact) {
        String sql = "INSERT INTO contact (full_name, email, message) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, contact.getFull_name(), contact.getEmail(), contact.getMessage());
    }
 
    
}