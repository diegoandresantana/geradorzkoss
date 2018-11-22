/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import annotations.ZKCombo;
import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Lancamento")
@Table(name = "lancamento", catalog = "controlealuguel", schema = "public")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
    @SequenceGenerator(name = "idlancamento", sequenceName = "lancamento_idlancamento_seq")
	@GeneratedValue(generator = "idlancamento", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idlancamento", nullable = false)
    private Integer idlancamento;
    @ZKField(label="Descricao",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
    
    @ZKField(label="Valor",nullable=false)
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private Double valor;
    
    @ZKField(label="Data do Vencimento",nullable=false)
    @Basic(optional = false)
    @Column(name = "datavencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    
    @ZKField(label="Data do Pagamento",nullable=false)   
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;
    @ZKField(label="Periodo de Referência",maxsize=30)  
    @Column(name = "periodoreferencia",  length = 30)
    private String periodoreferencia;
    

	@ZKCombo(label="Conta",nullable=false,propDisplayCombo="nomeconta")
    @JoinColumn(name = "conta", referencedColumnName = "idconta", nullable = false)
    @ManyToOne(optional = false)
    private Conta conta;
    
    @ZKFieldFind(label="Contrato",nullable=false,propDisplayFieldFind="idcontrato")
    @JoinColumn(name = "contrato", referencedColumnName = "idcontrato", nullable = false)
    @ManyToOne(optional = false)
    private Contrato contrato;
    
    @ZKCombo(label="Status do Lancamento",nullable=false,propDisplayCombo="descricao")
    @JoinColumn(name = "statuslancamento", referencedColumnName = "idstatuslancamento", nullable = false)
    @ManyToOne(optional = false)
    private StatusLancamento statuslancamento;

    
    public Lancamento() {
    }

    public Lancamento(Integer idlancamento) {
        this.idlancamento = idlancamento;
    }

    public Lancamento(Integer idlancamento, String descricao, Double valor, Date datavencimento) {
        this.idlancamento = idlancamento;
        this.descricao = descricao;
        this.valor = valor;
        this.datavencimento = datavencimento;
    }

    public Integer getIdlancamento() {
        return idlancamento;
    }

    public void setIdlancamento(Integer idlancamento) {
        this.idlancamento = idlancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public StatusLancamento getStatuslancamento() {
        return statuslancamento;
    }

    public void setStatuslancamento(StatusLancamento statuslancamento) {
        this.statuslancamento = statuslancamento;
    }

    public Date getDatapagamento() {
		return datapagamento;
	}

	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlancamento != null ? idlancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.idlancamento == null && other.idlancamento != null) || (this.idlancamento != null && !this.idlancamento.equals(other.idlancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Lancamento[idlancamento=" + idlancamento + "]";
    }

	public void setPeriodoreferencia(String periodoreferencia) {
		this.periodoreferencia = periodoreferencia;
	}

	public String getPeriodoreferencia() {
		return periodoreferencia;
	}

}
