package model.entity.hibernate;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKId;

/**
 *
 * @author diego
 */
@ZKEntity(label="IGP-M")
@Entity
@Table(name = "igpm", catalog = "controlealuguel", schema = "public", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"mes", "ano"})})
public class Igpm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ZKId
    @ZKField(label="Codigo",readonly=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idigmp", nullable = false)
    private Integer idigmp;
    
    @ZKField(label="Indice",nullable=false)
    @Basic(optional = false)
    @Column(name = "indice", nullable = false)
    private Double indice;
    @ZKComboFix(label="Mês",nullable=false,properties={"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Junho","Agosto","Setembro","Outubro","Novembro","Dezembro"},values={"1","2","3","4","5","6","7","8","9","10","11","12"})
    @Basic(optional = false)
    @Column(name = "mes", nullable = false)
    private Integer mes;
    @ZKComboFix(label="Ano",nullable=false,properties={"2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"},values={"2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"})
    @Basic(optional = false)
    @Column(name = "ano", nullable = false)
    private Integer ano;

    
    public Igpm() {
    }


    public Integer getIdigmp() {
        return idigmp;
    }

    public void setIdigmp(Integer idigmp) {
        this.idigmp = idigmp;
    }

    public Double getIndice() {
        return indice;
    }

    public void setIndice(Double indice) {
        this.indice = indice;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idigmp != null ? idigmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Igpm)) {
            return false;
        }
        Igpm other = (Igpm) object;
        if ((this.idigmp == null && other.idigmp != null) || (this.idigmp != null && !this.idigmp.equals(other.idigmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlealuguel.Igpm[idigmp=" + idigmp + "]";
    }

}
