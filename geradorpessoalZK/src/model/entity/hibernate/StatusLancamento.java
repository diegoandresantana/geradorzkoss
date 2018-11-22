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
@Table(name = "statuslancamento", catalog = "controlealuguel", schema = "public")
@ZKEntity(label="Status do Lancamento")
public class StatusLancamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
          @SequenceGenerator(name = "idstatusconta", sequenceName = "statusconta_idstatusconta_seq")
	@GeneratedValue(generator = "idstatusconta", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idstatuslancamento", nullable = false)
    private Integer idstatuslancamento;
    
    @ZKField(label="Sigla",nullable=false,maxsize=2)
    @Basic(optional = false)
    @Column(name = "sigla", nullable = false, length = 2)
    private String sigla;
    
    @ZKField(label="Descricao",nullable=false,maxsize=70)
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 70)
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statuslancamento")
    private List<Lancamento> lancamentoList;

    public StatusLancamento() {
    }

    public StatusLancamento(Integer idstatuslancamento) {
        this.idstatuslancamento = idstatuslancamento;
    }

    public StatusLancamento(Integer idstatuslancamento, String sigla, String descricao) {
        this.idstatuslancamento = idstatuslancamento;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public Integer getIdstatuslancamento() {
        return idstatuslancamento;
    }

    public void setIdstatuslancamento(Integer idstatuslancamento) {
        this.idstatuslancamento = idstatuslancamento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (idstatuslancamento != null ? idstatuslancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusLancamento)) {
            return false;
        }
        StatusLancamento other = (StatusLancamento) object;
        if ((this.idstatuslancamento == null && other.idstatuslancamento != null) || (this.idstatuslancamento != null && !this.idstatuslancamento.equals(other.idstatuslancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.StatusLancamento[idstatuslancamento=" + idstatuslancamento + "]";
    }

}
