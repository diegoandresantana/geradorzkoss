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
import javax.persistence.UniqueConstraint;

import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@Entity
@ZKEntity(label="Dono")
@Table(name = "dono", catalog = "controlealuguel", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nomedono"})})
public class Dono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
        @SequenceGenerator(name = "iddono", sequenceName = "dono_iddono_seq")
	@GeneratedValue(generator = "iddono", strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "iddono", nullable = false)
    private Integer iddono;
    
    @ZKField(label="Nome do Dono", nullable = false, maxsize = 200)
    @Basic(optional = false)
    @Column(name = "nomedono", nullable = false, length = 200)
    private String nomedono;
    
    @ZKField(label="CPF",  maxsize = 14)
    @Column(name = "cpf", length = 14)
    private String cpf;
    
    @ZKField(label="Telefone",  maxsize = 15)
    @Column(name = "telefone", length = 15)
    private String telefone;
    
    @ZKField(label="Celular",  maxsize = 15)
    @Column(name = "celular", length = 15)
    private String celular;
    
    @ZKField(label="RG",  maxsize = 15, nullable = false)
    @Basic(optional = false)
    @Column(name = "rg", nullable = false, length = 15)
    private String rg;
    
    @ZKField(label="E-mail",  maxsize = 150)
    @Column(name = "email", length = 150)
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dono")
    private List<Imovel> imovelList;
    
    @ZKFieldFind(label="Cidade",nullable=false,propDisplayFieldFind="nomecidade")
    @JoinColumn(name = "cidade", referencedColumnName = "idcidade", nullable = false)
    @ManyToOne(optional = false)
    private Cidade cidade;

    @ZKComboFix(label="Tem procurador",nullable=false,properties={"Sim","Nao"},values={"S","N"})
    @Column(name = "procurador", length = 1)
    private String procurador;
    
    @ZKField(label="Nome do Procurador",  maxsize = 150)
    @Column(name = "nomeprocurador", length = 150)
    private String nomeprocurador;
    
    @ZKField(label="RG do Procurador",  maxsize = 15)
    @Column(name = "rgprocurador", length = 15)
    private String rgprocurador;
    
    @ZKField(label="CPF do Procurador",  maxsize = 14)
    @Column(name = "cpfprocurador", length = 14)
    private String cpfprocurador;
    
    @ZKField(label="E-mail do Procurador",  maxsize = 150)
    @Column(name = "emailprocurador", length = 150)
    private String emailprocurador;
    
    @ZKField(label="Dono da Conta Bancaria",nullable=false,  maxsize = 200)
    @Column(name = "donocontabancaria", length = 200)
    private String donocontabancaria;

   

	@ZKFieldFind(label="Agencia",nullable=false,propDisplayFieldFind="numeroagencia")
    @JoinColumn(name = "agencia", referencedColumnName = "idagencia")
    @ManyToOne
    private Agencia agencia;
    
	
	 @ZKField(label="Conta Bancária",  maxsize = 20,nullable=false)
	@Column(name = "contabancaria", length = 20)
	    private String contabancaria;
   

	public Dono() {
    }

    public Dono(Integer iddono) {
        this.iddono = iddono;
    }

    public Dono(Integer iddono, String nomedono, String rg) {
        this.iddono = iddono;
        this.nomedono = nomedono;
        this.rg = rg;
    }

    public Integer getIddono() {
        return iddono;
    }

    public void setIddono(Integer iddono) {
        this.iddono = iddono;
    }

    public String getNomedono() {
        return nomedono;
    }

    public void setNomedono(String nomedono) {
        this.nomedono = nomedono;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Imovel> getImovelList() {
        return imovelList;
    }

    public void setImovelList(List<Imovel> imovelList) {
        this.imovelList = imovelList;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    public String getProcurador() {
		return procurador;
	}

	public void setProcurador(String procurador) {
		this.procurador = procurador;
	}

	public String getNomeprocurador() {
		return nomeprocurador;
	}

	public void setNomeprocurador(String nomeprocurador) {
		this.nomeprocurador = nomeprocurador;
	}

	public String getRgprocurador() {
		return rgprocurador;
	}

	public void setRgprocurador(String rgprocurador) {
		this.rgprocurador = rgprocurador;
	}

	public String getCpfprocurador() {
		return cpfprocurador;
	}

	public void setCpfprocurador(String cpfprocurador) {
		this.cpfprocurador = cpfprocurador;
	}

	public String getEmailprocurador() {
		return emailprocurador;
	}

	public void setEmailprocurador(String emailprocurador) {
		this.emailprocurador = emailprocurador;
	}

	public String getDonocontabancaria() {
		return donocontabancaria;
	}

	public void setDonocontabancaria(String donocontabancaria) {
		this.donocontabancaria = donocontabancaria;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	 public String getContabancaria() {
			return contabancaria;
		}

		public void setContabancaria(String contabancaria) {
			this.contabancaria = contabancaria;
		}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddono != null ? iddono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dono)) {
            return false;
        }
        Dono other = (Dono) object;
        if ((this.iddono == null && other.iddono != null) || (this.iddono != null && !this.iddono.equals(other.iddono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Dono[iddono=" + iddono + "]";
    }

}
