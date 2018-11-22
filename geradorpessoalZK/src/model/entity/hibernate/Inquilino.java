/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity.hibernate;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Inquilino")
@Table(name = "inquilino", catalog = "controlealuguel", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cpf"})})
public class Inquilino implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
    @SequenceGenerator(name = "idinquilino", sequenceName = "inquilino_idinquilino_seq")
	@GeneratedValue(generator = "idinquilino", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idinquilino", nullable = false)
    private Integer idinquilino;
    
    @ZKField(label="Nome do Inquilino",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "nomeinquilino", nullable = false, length = 200)
    private String nomeinquilino;
    
    @ZKField(label="RG",nullable=false,maxsize=15)
    @Basic(optional = false)
    @Column(name = "rg", nullable = false, length = 15)
    private String rg;
    
    @ZKField(label="CPF",nullable=false,maxsize=14)
    @Basic(optional = false)
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    
    @ZKField(label="Telefone",maxsize=13)
    @Column(name = "telefone", length = 13)
    private String telefone;
    
    @ZKField(label="Celular",maxsize=13)
    @Column(name = "celular", length = 13)
    private String celular;
    
    @ZKField(label="E-mail",maxsize=120)
    @Column(name = "email", length = 120)
    private String email;
    
    @ZKField(label="Data do Nascimento",nullable=false)
    @Basic(optional = false)
    @Column(name = "datanascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    
    @ZKField(label="Nome do Pai",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "nomepai", nullable = false, length = 200)
    private String nomepai;
    
    @ZKField(label="Nome da Mae",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "nomemae", nullable = false, length = 200)
    private String nomemae;
    
    
    @ZKFieldFind(label="Cidade",nullable=false,propDisplayFieldFind="nomecidade")
    @JoinColumn(name = "cidade", referencedColumnName = "idcidade", nullable = false)
    @ManyToOne(optional = false)
    private Cidade cidade;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inquilino")
    private List<Contrato> contratoList;

    public Inquilino() {
    }

    public Inquilino(Integer idinquilino) {
        this.idinquilino = idinquilino;
    }

    public Inquilino(Integer idinquilino, String nomeinquilino, String rg, String cpf, Date datanascimento, String nomepai, String nomemae) {
        this.idinquilino = idinquilino;
        this.nomeinquilino = nomeinquilino;
        this.rg = rg;
        this.cpf = cpf;
        this.datanascimento = datanascimento;
        this.nomepai = nomepai;
        this.nomemae = nomemae;
    }

    public Integer getIdinquilino() {
        return idinquilino;
    }

    public void setIdinquilino(Integer idinquilino) {
        this.idinquilino = idinquilino;
    }

    public String getNomeinquilino() {
        return nomeinquilino;
    }

    public void setNomeinquilino(String nomeinquilino) {
        this.nomeinquilino = nomeinquilino;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getNomepai() {
        return nomepai;
    }

    public void setNomepai(String nomepai) {
        this.nomepai = nomepai;
    }

    public String getNomemae() {
        return nomemae;
    }

    public void setNomemae(String nomemae) {
        this.nomemae = nomemae;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinquilino != null ? idinquilino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inquilino)) {
            return false;
        }
        Inquilino other = (Inquilino) object;
        if ((this.idinquilino == null && other.idinquilino != null) || (this.idinquilino != null && !this.idinquilino.equals(other.idinquilino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Inquilino[idinquilino=" + idinquilino + "]";
    }

}
