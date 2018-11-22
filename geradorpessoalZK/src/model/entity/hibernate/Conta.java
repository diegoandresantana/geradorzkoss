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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import annotations.ZKCombo;
import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Conta")
@Table(name = "conta", catalog = "controlealuguel", schema = "public")
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
          @SequenceGenerator(name = "idconta", sequenceName = "conta_idconta_seq")
	@GeneratedValue(generator = "idconta", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idconta", nullable = false)
    private Integer idconta;
    
    @ZKField(label="Nome da Conta", nullable = false,maxsize= 200)
    @Basic(optional = false)
    @Column(name = "nomeconta", nullable = false, length = 200)
    private String nomeconta;
    
    @ZKCombo(label="Tipo de Conta",nullable=false,propDisplayCombo="nomeconta")
    @JoinColumn(name = "tipoconta", referencedColumnName = "idtipoconta", nullable = false)
    @ManyToOne(optional = false)
    private TipoConta tipoconta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private List<Lancamento> lancamentoList;

    public Conta() {
    }

    public Conta(Integer idconta) {
        this.idconta = idconta;
    }

    public Conta(Integer idconta, String nomeconta) {
        this.idconta = idconta;
        this.nomeconta = nomeconta;
    }

    public Integer getIdconta() {
        return idconta;
    }

    public void setIdconta(Integer idconta) {
        this.idconta = idconta;
    }

    public String getNomeconta() {
        return nomeconta;
    }

    public void setNomeconta(String nomeconta) {
        this.nomeconta = nomeconta;
    }

    public TipoConta getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(TipoConta tipoconta) {
        this.tipoconta = tipoconta;
    }

    public List<Lancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconta != null ? idconta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.idconta == null && other.idconta != null) || (this.idconta != null && !this.idconta.equals(other.idconta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Conta[idconta=" + idconta + "]";
    }

}
