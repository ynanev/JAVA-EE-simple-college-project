/*

Yavor Nanev,           11060247
 */
package jjb;

import beans.CartBeanLocal;
import beans.beanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rock n Roll
 */
public class CurUs {

    
    beanLocal  bean = lookupbeanLocal();
    
    private static String name;
    private static int id=0;
    private static CartBeanLocal cartBean=null;
    private static String reqName=null;
    private static String usertyppe=null;
    private static boolean currentUserLogedIn=true;

    public static void setCurrentUserLogedIn(boolean currentUserLogedIn) {
        CurUs.currentUserLogedIn = currentUserLogedIn;
    }
    
    public static boolean isCurrentUserLogedIn() {
        return currentUserLogedIn;
    }
    
    public beanLocal getBean() {
        return bean;
    }

    public static String getUsertyppe() {
        return usertyppe;
    }
    public static void setReqName(String reqName) {
        CurUs.reqName = reqName;
    }

    public static void setName(String name) {
        CurUs.name = name;
    }

    public static void setId(int id) {
        CurUs.id = id;
    }

    public static void setCartBean(CartBeanLocal cartBean) {
        CurUs.cartBean = cartBean;
    }
    public static String getName() {
        return name;
    }

    public static int getId() {
        return id;
    }

    public static CartBeanLocal getCartBean() {
        return cartBean;
    }

    public static String getReqName() {
        return reqName;
    }

    /**
     *
     */
    public static void setUsertyppe(String t) {
      
        usertyppe = t;
    }

    public CurUs(String name,int id) {
        
        
    }

    private beanLocal lookupbeanLocal() {
        try {
            Context c = new InitialContext();
            return (beanLocal) c.lookup("java:global/newtwo/newtwo-ejb/bean!beans.beanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

   
    
}
