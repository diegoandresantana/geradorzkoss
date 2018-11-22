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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Contrato")
@Table(name = "contrato", catalog = "controlealuguel", schema = "public")
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
     @SequenceGenerator(name = "idcontrato", sequenceName = "contrato_idcontrato_seq")
	@GeneratedValue(generator = "idcontrato", strategy = GenerationType.AUTO)

    @Basic(optional = false)
    @Column(name = "idcontrato", nullable = false)
    private Integer idcontrato;
    
    @ZKField(label="Fiador",maxsize=200)
    @Basic(optional = false)
    @Column(name = "fiador", length = 200)
    private String fiador;
    
    @ZKField(label="Endereco do Fiador",maxsize=200)
    @Column(name = "enderecofiador", length = 200)
    private String enderecofiador;
    
    @ZKField(label="Numero",maxsize=7)
    @Column(name = "numerofiador", length = 7)
    private String numerofiador;
    
    @ZKField(label="Bairro",maxsize=200)
    @Column(name = "bairrofiador", length = 200)
    private String bairrofiador;
    
    @ZKField(label="Celular",maxsize=13)
    @Column(name = "celularfiador", length = 13)
    private String celularfiador;
    
    @ZKField(label="Telefone",maxsize=13)
    @Column(name = "telefonefiador", length = 13)
    private String telefonefiador;
    
    @ZKField(label="CPF",maxsize=14)
    @Column(name = "cpffiado", length = 14)
    private String cpffiado;
    
    @ZKField(label="RG",maxsize=15)
    @Column(name = "rgfiador", length = 15)
    private String rgfiador;
    
    @ZKField(label="Data Inicial do Contrato",nullable=false)
    @Basic(optional = false)
    @Column(name = "datainicialcontrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datainicialcontrato;
    
    @ZKField(label="Data Final do Contrato",nullable=false)
    @Basic(optional = false)
    @Column(name = "datafinalcontrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datafinalcontrato;
    
    @ZKField(label="Data do Primeiro Pagamento",nullable=false)
    @Basic(optional = false)
    @Column(name = "dataprimeiropagamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataprimeiropagamento;
    
   

	@ZKField(label="Numero de Meses",nullable=false)
    @Basic(optional = false)
    @Column(name = "numeromeses", nullable = false)
    private Integer numeromeses;
    
    @ZKField(label="Numero de Meses Literal",maxsize=20,nullable=false)
    @Basic(optional = false)
    @Column(name = "numeromesesliteral", nullable = false, length = 20)
    private String numeromesesliteral;
    
    @ZKField(label="Valor do Aluguel",nullable=false)
    @Basic(optional = false)
    @Column(name = "valoraluguel", nullable = false)
    private Double valoraluguel;
    
    @ZKField(label="Valor do Aluguel Literal",maxsize=35,nullable=false)
    @Basic(optional = false)
    @Column(name = "valoraluguelliteral", nullable = false, length = 35)
    private String valoraluguelliteral;
    
    @ZKField(label="Dia do Pagamento",nullable=false)
    @Basic(optional = false)
    @Column(name = "diapagamento", nullable = false)
    private Integer diapagamento;
    
    @ZKField(label="Dia do Pagamento Literal",maxsize=20,nullable=false)
    @Basic(optional = false)
    @Column(name = "diapagamentoliteral", nullable = false, length = 20)
    private String diapagamentoliteral;
    
    @ZKFieldFind(label="Imovel",nullable=false,propDisplayFieldFind="nomeimovel")
    @JoinColumn(name = "imovel", referencedColumnName = "idimovel", nullable = false)
    @ManyToOne(optional = false)
    private Imovel imovel;
    
    @ZKFieldFind(label="Inquilino",nullable=false,propDisplayFieldFind="nomeinquilino")
    @JoinColumn(name = "inquilino", referencedColumnName = "idinquilino", nullable = false)
    @ManyToOne(optional = false)
    private Inquilino inquilino;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private List<Lancamento> lancamentoList;

    public Contrato() {
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getFiador() {
        return fiador;
    }

    public void setFiador(String fiador) {
        this.fiador = fiador;
    }

    public String getEnderecofiador() {
        return enderecofiador;
    }

    public void setEnderecofiador(String enderecofiador) {
        this.enderecofiador = enderecofiador;
    }

    public String getNumerofiador() {
        return numerofiador;
    }

    public void setNumerofiador(String numerofiador) {
        this.numerofiador = numerofiador;
    }

    public String getBairrofiador() {
        return bairrofiador;
    }

    public void setBairrofiador(String bairrofiador) {
        this.bairrofiador = bairrofiador;
    }

    public String getCelularfiador() {
        return celularfiador;
    }

    public void setCelularfiador(String celularfiador) {
        this.celularfiador = celularfiador;
    }

    public String getTelefonefiador() {
        return telefonefiador;
    }

    public void setTelefonefiador(String telefonefiador) {
        this.telefonefiador = telefonefiador;
    }

    public String getCpffiado() {
        return cpffiado;
    }

    public void setCpffiado(String cpffiado) {
        this.cpffiado = cpffiado;
    }

    public String getRgfiador() {
        return rgfiador;
    }

    public void setRgfiador(String rgfiador) {
        this.rgfiador = rgfiador;
    }

    public Date getDatainicialcontrato() {
        return datainicialcontrato;
    }

    public void setDatainicialcontrato(Date datainicialcontrato) {
        this.datainicialcontrato = datainicialcontrato;
    }

    public Date getDatafinalcontrato() {
        return datafinalcontrato;
    }

    public void setDatafinalcontrato(Date datafinalcontrato) {
        this.datafinalcontrato = datafinalcontrato;
    }

    public Integer getNumeromeses() {
        return numeromeses;
    }

    public void setNumeromeses(Integer numeromeses) {
        this.numeromeses = numeromeses;
    }

    public String getNumeromesesliteral() {
        return numeromesesliteral;
    }

    public void setNumeromesesliteral(String numeromesesliteral) {
        this.numeromesesliteral = numeromesesliteral;
    }

    public Double getValoraluguel() {
        return valoraluguel;
    }

    public void setValoraluguel(Double valoraluguel) {
        this.valoraluguel = valoraluguel;
    }

    public String getValoraluguelliteral() {
        return valoraluguelliteral;
    }

    public void setValoraluguelliteral(String valoraluguelliteral) {
        this.valoraluguelliteral = valoraluguelliteral;
    }

    public Integer getDiapagamento() {
        return diapagamento;
    }

    public void setDiapagamento(Integer diapagamento) {
        this.diapagamento = diapagamento;
    }

    public String getDiapagamentoliteral() {
        return diapagamentoliteral;
    }

    public void setDiapagamentoliteral(String diapagamentoliteral) {
        this.diapagamentoliteral = diapagamentoliteral;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public List<Lancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }
    public Date getDataprimeiropagamento() {
		return dataprimeiropagamento;
	}

	public void setDataprimeiropagamento(Date dataprimeiropagamento) {
		this.dataprimeiropagamento = dataprimeiropagamento;
	}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Contrato[idcontrato=" + idcontrato + "]";
    }

}
