package org.tools.hqlbuilder.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.tools.hqlbuilder.common.ERHAdapter;
import org.tools.hqlbuilder.common.EntityI;
import org.tools.hqlbuilder.common.EntityRelationHelper;

@XmlRootElement
@Entity
@Table(name = "group_members")
public class Member extends ERHAdapter implements EntityI, MemberProperties {
    private static final long serialVersionUID = -6012619912508524393L;

    @Version
    private Integer version;

    @Id
    private String username;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull
    private Group group;

    @OneToMany(mappedBy = Authority.MEMBER)
    private List<Authority> authorities;

    public Member() {
        erh = new EntityRelationHelper<Member>(this);
    }

    public Member(String username, Group group) {
        this();
        setUsername(username);
        setGroup(group);
    }

    @Override
    public String toString() {
        return getUsername();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        erh.moSet(GROUP, group);
    }

    public List<Authority> getAuthorities() {
        return erh.omGet(AUTHORITIES, this.authorities);
    }

    public void setAuthorities(List<Authority> authorities) {
        erh.omSet(AUTHORITIES, authorities);
    }

    public void addAuthority(Authority authority) {
        erh.omAdd(AUTHORITIES, authority);
    }

    public void removeAuthority(Authority authority) {
        erh.omRemove(AUTHORITIES, authority);
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
