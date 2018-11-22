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
@ZKEntity(label="Cidade",tipoPaginacao=1)
@Table(name = "cidade", catalog = "controlealuguel", schema = "public")
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
   @SequenceGenerator(name = "idcidade", sequenceName = "cidade_idcidade_seq")
	@GeneratedValue(generator = "idcidade", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idcidade", nullable = false)
    private Integer idcidade;
    
    @ZKField(label="Nome da Cidade")
    @Basic(optional = false)
    @Column(name = "nomecidade", nullable = false, length = 150)
    private String nomecidade;
    
    @ZKCombo(label="Estado",nullable=false,propDisplayCombo="uf")
    @JoinColumn(name = "estado", referencedColumnName = "idestado", nullable = false)
    @ManyToOne(optional = false)
    private Estado estado;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade")
    private List<Inquilino> inquilinoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade")
    private List<Imovel> imovelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade")
    private List<Dono> donoList;

    public Cidade() {
    }

    public Cidade(Integer idcidade) {
        this.idcidade = idcidade;
    }

    public Cidade(Integer idcidade, String nomecidade) {
        this.idcidade = idcidade;
        this.nomecidade = nomecidade;
    }

    public Integer getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Integer idcidade) {
        this.idcidade = idcidade;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Inquilino> getInquilinoList() {
        return inquilinoList;
    }

    public void setInquilinoList(List<Inquilino> inquilinoList) {
        this.inquilinoList = inquilinoList;
    }

    public List<Imovel> getImovelList() {
        return imovelList;
    }

    public void setImovelList(List<Imovel> imovelList) {
        this.imovelList = imovelList;
    }

    public List<Dono> getDonoList() {
        return donoList;
    }

    public void setDonoList(List<Dono> donoList) {
        this.donoList = donoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcidade != null ? idcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.idcidade == null && other.idcidade != null) || (this.idcidade != null && !this.idcidade.equals(other.idcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Cidade[idcidade=" + idcidade + "]";
    }

}
