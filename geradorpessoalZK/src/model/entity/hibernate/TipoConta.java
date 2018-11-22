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

import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "tipoconta", catalog = "controlealuguel", schema = "public")
@ZKEntity(label="Tipo de Conta")
public class TipoConta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
      @SequenceGenerator(name = "idtipoconta", sequenceName = "tipoconta_idtipoconta_seq")
	@GeneratedValue(generator = "idtipoconta", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idtipoconta", nullable = false)
    private Integer idtipoconta;
    @ZKField(label="Nome da Conta",nullable=false,maxsize=100)
    @Basic(optional = false)    
    @Column(name = "nomeconta", nullable = false, length = 100)
    private String nomeconta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoconta")
    private List<Conta> contaList;

    public TipoConta() {
    }

    public TipoConta(Integer idtipoconta) {
        this.idtipoconta = idtipoconta;
    }

    public TipoConta(Integer idtipoconta, String nomeconta) {
        this.idtipoconta = idtipoconta;
        this.nomeconta = nomeconta;
    }

    public Integer getIdtipoconta() {
        return idtipoconta;
    }

    public void setIdtipoconta(Integer idtipoconta) {
        this.idtipoconta = idtipoconta;
    }

    public String getNomeconta() {
        return nomeconta;
    }

    public void setNomeconta(String nomeconta) {
        this.nomeconta = nomeconta;
    }

    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoconta != null ? idtipoconta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConta)) {
            return false;
        }
        TipoConta other = (TipoConta) object;
        if ((this.idtipoconta == null && other.idtipoconta != null) || (this.idtipoconta != null && !this.idtipoconta.equals(other.idtipoconta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.TipoConta[idtipoconta=" + idtipoconta + "]";
    }

}
