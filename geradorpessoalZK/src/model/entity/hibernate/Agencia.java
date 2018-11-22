/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity.hibernate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@ZKEntity(label="Agencia")
@Table(name = "agencia", catalog = "controlealuguel", schema = "public")
public class Agencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
            @SequenceGenerator(name = "idagencia", sequenceName = "agencia_idagencia_seq")
	@GeneratedValue(generator = "idagencia", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idagencia", nullable = false)
    private Integer idagencia;
    
    @ZKField(label="Nome da Agencia",  maxsize = 150)
    @Basic(optional = false)
    @Column(name = "nomeagencia", nullable = false, length = 150)
    private String nomeagencia;
    
    @ZKField(label="Numero da Agencia",  maxsize = 30)
    @Basic(optional = false)
    @Column(name = "numeroagencia", nullable = false, length = 30)
    private String numeroagencia;
    
    @OneToMany(mappedBy = "agencia")
    private List<Dono> donoList;
    
    @ZKCombo(label="Banco",nullable=false,propDisplayCombo="nomebanco")
    @JoinColumn(name = "banco", referencedColumnName = "idbanco", nullable = false)
    @ManyToOne(optional = false)
    private Banco banco;

    public Agencia() {
    }



    public Integer getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Integer idagencia) {
        this.idagencia = idagencia;
    }

    public String getNomeagencia() {
        return nomeagencia;
    }

    public void setNomeagencia(String nomeagencia) {
        this.nomeagencia = nomeagencia;
    }

    public String getNumeroagencia() {
        return numeroagencia;
    }

    public void setNumeroagencia(String numeroagencia) {
        this.numeroagencia = numeroagencia;
    }

    public List<Dono> getDonoList() {
        return donoList;
    }

    public void setDonoList(List<Dono> donoList) {
        this.donoList = donoList;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagencia != null ? idagencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencia)) {
            return false;
        }
        Agencia other = (Agencia) object;
        if ((this.idagencia == null && other.idagencia != null) || (this.idagencia != null && !this.idagencia.equals(other.idagencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Agencia[idagencia=" + idagencia + "]";
    }

}
