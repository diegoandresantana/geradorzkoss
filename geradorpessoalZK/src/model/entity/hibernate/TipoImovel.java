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
@Table(name = "tipoimovel", catalog = "controlealuguel", schema = "public")
@ZKEntity(label="Tipo de Imovel")
public class TipoImovel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
   @SequenceGenerator(name = "idtipoimovel", sequenceName = "tipoimovel_idtipoimovel_seq")
	@GeneratedValue(generator = "idtipoimovel", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idtipoimovel", nullable = false)
    private Integer idtipoimovel;
    
    @ZKField(label="Descricao",nullable=false,maxsize=100)
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoimovel")
    private List<Imovel> imovelList;

    public TipoImovel() {
    }

    public TipoImovel(Integer idtipoimovel) {
        this.idtipoimovel = idtipoimovel;
    }

    public TipoImovel(Integer idtipoimovel, String descricao) {
        this.idtipoimovel = idtipoimovel;
        this.descricao = descricao;
    }

    public Integer getIdtipoimovel() {
        return idtipoimovel;
    }

    public void setIdtipoimovel(Integer idtipoimovel) {
        this.idtipoimovel = idtipoimovel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Imovel> getImovelList() {
        return imovelList;
    }

    public void setImovelList(List<Imovel> imovelList) {
        this.imovelList = imovelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoimovel != null ? idtipoimovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoImovel)) {
            return false;
        }
        TipoImovel other = (TipoImovel) object;
        if ((this.idtipoimovel == null && other.idtipoimovel != null) || (this.idtipoimovel != null && !this.idtipoimovel.equals(other.idtipoimovel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.TipoImovel[idtipoimovel=" + idtipoimovel + "]";
    }

}
