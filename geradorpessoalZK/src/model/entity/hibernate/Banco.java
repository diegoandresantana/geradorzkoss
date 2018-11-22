/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@ZKEntity(label="Banco")
@Entity
@Table(name = "banco", catalog = "controlealuguel", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nomebanco"})})
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
    @SequenceGenerator(name = "idbanco", sequenceName = "banco_idbanco_seq")
	@GeneratedValue(generator = "idbanco", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idbanco", nullable = false)
    private Integer idbanco;
    
    @ZKField(label="Nome do Banco",  maxsize = 150)
    @Basic(optional = false)
    @Column(name = "nomebanco", nullable = false, length = 150)
    private String nomebanco;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Agencia> agenciaList;

    public Banco() {
    }

  

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }

    public List<Agencia> getAgenciaList() {
        return agenciaList;
    }

    public void setAgenciaList(List<Agencia> agenciaList) {
        this.agenciaList = agenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Banco[idbanco=" + idbanco + "]";
    }

}
