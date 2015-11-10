/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ente;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rock n Roll
 */
@Entity
@Table(name = "BOOK_COMMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookComments.findAll", query = "SELECT b FROM BookComments b"),
    @NamedQuery(name = "BookComments.findById", query = "SELECT b FROM BookComments b WHERE b.id = :id"),
    @NamedQuery(name = "BookComments.findByBookIsbn", query = "SELECT b FROM BookComments b WHERE b.bookIsbn = :bookIsbn"),
    @NamedQuery(name = "BookComments.findByUsername", query = "SELECT b FROM BookComments b WHERE b.username = :username"),
    @NamedQuery(name ="BookComments.getHighestCustomerID", query = "SELECT MAX(b.id) from BookComments b")})
public class BookComments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BOOK_ISBN")
    private Integer bookIsbn;
    @Lob
    @Size(max = 32700)
    @Column(name = "COMMENT")
    private String comment;
    @Size(max = 70)
    @Column(name = "USERNAME")
    private String username;

    public BookComments() {
    }

    public BookComments(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(Integer bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookComments)) {
            return false;
        }
        BookComments other = (BookComments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ente.BookComments[ id=" + id + " ]";
    }
    
}
