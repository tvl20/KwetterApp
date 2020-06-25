package com.kwetter.serviceusers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    @Column(columnDefinition = "VARCHAR(15)")
    private String username = "";
    private String mentionHandle = "";
    private String description = "";

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<UserEntity> following = new HashSet<>();

    public String getMentionHandle()
    {
        return mentionHandle;
    }

    public void setMentionHandle(String mentionHandle)
    {
        this.mentionHandle = mentionHandle;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String desc)
    {
        this.description = desc;
    }

    public Set<UserEntity> getFollowing()
    {
        return following;
    }

    public void setFollowing(Set<UserEntity> following)
    {
        this.following = following;
    }

    public void handleFollow(UserEntity user)
    {
        if (this.following.contains(user))
        {
            this.following.remove(user);
            System.out.println(username + " stopped following " + user.getUsername());
        }
        else
        {
            this.following.add(user);
            System.out.println(username + " is now following " + user.getUsername());
        }
    }

    public UserEntity() { }

    public UserEntity(String username, String mentionHandle)
    {
        this.username = username;
        this.mentionHandle = mentionHandle;
    }

    @Override
    public String toString()
    {
        return "UserEntity{" +
               "username='" + username + '\'' +
               ", mentionHandle='" + mentionHandle + '\'' +
               ", description='" + description + '\'' +
               ", following=" + following +
               '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return getUsername().equals(that.getUsername()) &&
               getMentionHandle().equals(that.getMentionHandle()) &&
               getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getMentionHandle(), getDescription());
    }
}
