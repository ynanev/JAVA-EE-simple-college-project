/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ente.Book;
import ente.Users;
import java.util.List;
import javax.ejb.Local;

/**
 
Yavor Nanev,           11060247
 */
@Local
public interface beanLocal {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean isIn(String username,String password);
    public Users user(String username);
    public List<Book> gbook();
}
