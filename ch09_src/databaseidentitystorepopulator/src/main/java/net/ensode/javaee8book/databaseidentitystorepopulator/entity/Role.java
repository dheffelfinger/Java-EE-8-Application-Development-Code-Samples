/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ensode.javaee8book.databaseidentitystorepopulator.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author heffel
 */
@Entity
@Table(name = "GROUPS")

@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
@NamedQuery(name = "Role.findByGroupId", query = "SELECT r FROM Role r WHERE r.groupId = :groupId")
@NamedQuery(name = "Role.findByGroupName", query = "SELECT r FROM Role r WHERE r.groupName = :groupName")
@NamedQuery(name = "Role.findByGroupNames", query = "SELECT r FROM Role r WHERE r.groupName IN :groupNames")
@NamedQuery(name = "Role.findByGroupDesc", query = "SELECT r FROM Role r WHERE r.groupDesc = :groupDesc")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "GROUP_ID")
    private Integer groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Size(max = 200)
    @Column(name = "GROUP_DESC")
    private String groupDesc;
    @ManyToMany(mappedBy = "roleCollection")
    private Collection<User> userCollection;

    public Role() {
    }

    public Role(Integer groupId) {
        this.groupId = groupId;
    }

    public Role(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ensode.javaee8book.databaseidentitystorepopulator.entity.Role[ groupId=" + groupId + " ]";
    }

}
