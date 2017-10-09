package net.ensode.javaee8book.criteriaupdate.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author heffel
 */
@Entity
@Table(name = "ADDRESS_TYPES")
@NamedQuery(name = "AddressType.findAll", query = "SELECT a FROM AddressType a")
public class AddressType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADDRESS_TYPE_ID")
    private Integer addressTypeId;
    @Column(name = "ADDRESS_TYPE_CODE")
    private Character addressTypeCode;
    @Size(max = 20)
    @Column(name = "ADDRESS_TYPE_TEXT")
    private String addressTypeText;
    @OneToMany(mappedBy = "addressTypeId")
    private Collection<Address> addressCollection;

    public AddressType() {
    }

    public AddressType(Integer addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public Integer getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(Integer addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public Character getAddressTypeCode() {
        return addressTypeCode;
    }

    public void setAddressTypeCode(Character addressTypeCode) {
        this.addressTypeCode = addressTypeCode;
    }

    public String getAddressTypeText() {
        return addressTypeText;
    }

    public void setAddressTypeText(String addressTypeText) {
        this.addressTypeText = addressTypeText;
    }

    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressTypeId != null ? addressTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AddressType)) {
            return false;
        }
        AddressType other = (AddressType) object;
        if ((this.addressTypeId == null && other.addressTypeId != null) || (this.addressTypeId != null && !this.addressTypeId.equals(other.addressTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ensode.glassfishbook.criteriaupdate.entity.AddressType[ addressTypeId=" + addressTypeId + " ]";
    }

}
