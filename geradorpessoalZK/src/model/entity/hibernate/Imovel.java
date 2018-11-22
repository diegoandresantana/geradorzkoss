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
import annotations.ZKFieldFind;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Imovel")
@Table(name = "imovel", catalog = "controlealuguel", schema = "public")
public class Imovel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
        @SequenceGenerator(name = "idimovel", sequenceName = "imovel_idimovel_seq")
	@GeneratedValue(generator = "idimovel", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idimovel", nullable = false)
    private Integer idimovel;
    
    @ZKField(label="Nome do Imovel",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "nomeimovel", nullable = false, length = 200)
    private String nomeimovel;
    
    @ZKField(label="Endereco",nullable=false,maxsize=200)
    @Basic(optional = false)
    @Column(name = "endereco", nullable = false, length = 200)
    private String endereco;
    
    @ZKField(label="Numero",nullable=false,maxsize=7)
    @Basic(optional = false)
    @Column(name = "numero", nullable = false, length = 7)
    private String numero;
    
    @ZKField(label="Bairro",nullable=false,maxsize=150)
    @Basic(optional = false)
    @Column(name = "bairro", nullable = false, length = 150)
    private String bairro;
    
    @ZKField(label="CEP",nullable=false,maxsize=9)
    @Basic(optional = false)
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @ZKField(label="Complemento",maxsize=200)

    @Column(name = "complemento", length = 200)
    private String complemento;
    
   

	@ZKFieldFind(label="Cidade",nullable=false,propDisplayFieldFind="nomecidade")
    @JoinColumn(name = "cidade", referencedColumnName = "idcidade", nullable = false)
    @ManyToOne(optional = false)
    private Cidade cidade;
    
    @ZKFieldFind(label="Dono",nullable=false,propDisplayFieldFind="nomedono")
    @JoinColumn(name = "dono", referencedColumnName = "iddono", nullable = false)
    @ManyToOne(optional = false)
    private Dono dono;
    
    @ZKCombo(label="Tipo Imovel", nullable=false,propDisplayCombo="descricao")
    @JoinColumn(name = "tipoimovel", referencedColumnName = "idtipoimovel", nullable = false)
    @ManyToOne(optional = false)
    private TipoImovel tipoimovel;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    private List<Contrato> contratoList;

    public Imovel() {
    }

    public Imovel(Integer idimovel) {
        this.idimovel = idimovel;
    }

    public Imovel(Integer idimovel, String nomeimovel, String endereco, String numero, String bairro, String cep) {
        this.idimovel = idimovel;
        this.nomeimovel = nomeimovel;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Integer getIdimovel() {
        return idimovel;
    }

    public void setIdimovel(Integer idimovel) {
        this.idimovel = idimovel;
    }

    public String getNomeimovel() {
        return nomeimovel;
    }

    public void setNomeimovel(String nomeimovel) {
        this.nomeimovel = nomeimovel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public TipoImovel getTipoimovel() {
        return tipoimovel;
    }

    public void setTipoimovel(TipoImovel tipoimovel) {
        this.tipoimovel = tipoimovel;
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }
    public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimovel != null ? idimovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) object;
        if ((this.idimovel == null && other.idimovel != null) || (this.idimovel != null && !this.idimovel.equals(other.idimovel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Imovel[idimovel=" + idimovel + "]";
    }

}
