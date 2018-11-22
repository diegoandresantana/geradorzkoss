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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@ZKEntity(label="Estado")
@Table(name = "estado", catalog = "controlealuguel", schema = "public")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
        @SequenceGenerator(name = "idestado", sequenceName = "estado_idestado_seq")
	@GeneratedValue(generator = "idestado", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idestado", nullable = false)
    private Integer idestado;
    
    @ZKField(label="Nome do Estado", nullable = false, maxsize= 120)
    @Basic(optional = false)
    @Column(name = "nomeestado", nullable = false, length = 120)
    private String nomeestado;
    
    @ZKField(label="UF", nullable = false, maxsize= 2)
    @Basic(optional = false)
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<Cidade> cidadeList;

    public Estado() {
    }

    public Estado(Integer idestado) {
        this.idestado = idestado;
    }

    public Estado(Integer idestado, String nomeestado, String uf) {
        this.idestado = idestado;
        this.nomeestado = nomeestado;
        this.uf = uf;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Estado[idestado=" + idestado + "]";
    }

}
